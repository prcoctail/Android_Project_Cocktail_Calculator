package ru.partyshaker.partyshaker.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.partyshaker.partyshaker.data.repositories.auth.AuthRepository
import ru.partyshaker.partyshaker.data.responses.auth.LoginErrorResponse
import ru.partyshaker.partyshaker.data.responses.auth.LoginResponse
import ru.partyshaker.partyshaker.data.responses.auth.ResendActivationErrorResponse
import ru.partyshaker.partyshaker.models.CheckLogin
import ru.partyshaker.partyshaker.models.ResendActivation
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private var _login: MutableLiveData<LoginResponse> = MutableLiveData()
    val login: LiveData<LoginResponse>
        get() = _login

    private var _loginError: MutableLiveData<LoginErrorResponse> = MutableLiveData()
    val loginError: LiveData<LoginErrorResponse>
        get() = _loginError

    private var _activationRequired: MutableLiveData<Boolean> = MutableLiveData()
    val activationRequired: LiveData<Boolean>
        get() = _activationRequired

    private var _activationRequiredError: MutableLiveData<ResendActivationErrorResponse> =
        MutableLiveData()
    val activationRequiredError: LiveData<ResendActivationErrorResponse>
        get() = _activationRequiredError

    fun clearActivation() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                _activationRequired.postValue(false)
            }
        }
    }

    fun checkLogin(email: String, pass: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.checkLogin(CheckLogin(email, pass))

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        _login.postValue(response.body())
                    } else if (response.code() == 400
                        || response.code() == 401
                    ) {
                        try {
                            val gson = Gson()
                            val loginErrorResponse = gson.fromJson(
                                response.errorBody()?.string(),
                                LoginErrorResponse::class.java
                            )

                            _loginError.postValue(loginErrorResponse)
                        } catch (e: Exception) {
                            Log.e("AUTH", e.message.toString())
                        }
                    } else if (response.code() == 403) {
                        activationRequired(email)
                    } else {
                        response.errorBody()?.string()?.let { Log.e("AUTH", it) }
                    }
                }
            }
        }
    }

    private fun activationRequired(email: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.resendActivation(ResendActivation(email))

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        _activationRequired.postValue(true)
                    } else if (response.code() == 400) {
                        try {
                            val gson = Gson()
                            val errorResponse = gson.fromJson(
                                response.errorBody()?.string(),
                                ResendActivationErrorResponse::class.java
                            )

                            _activationRequiredError.postValue(errorResponse)
                        } catch (e: Exception) {
                            Log.e("AUTH", e.message.toString())
                        }
                    } else {
                        response.errorBody()?.string()?.let { Log.e("AUTH", it) }
                    }
                }
            }
        }
    }
}