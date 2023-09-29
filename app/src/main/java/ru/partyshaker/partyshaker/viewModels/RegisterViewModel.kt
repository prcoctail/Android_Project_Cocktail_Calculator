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
import ru.partyshaker.partyshaker.data.responses.auth.CheckUserResponse
import ru.partyshaker.partyshaker.data.responses.auth.RegisterCheckUserResponse
import ru.partyshaker.partyshaker.data.responses.auth.RegisterResponse
import ru.partyshaker.partyshaker.models.CheckUser
import ru.partyshaker.partyshaker.models.Register
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private var _checkUser: MutableLiveData<CheckUserResponse> = MutableLiveData()
    val checkUser: LiveData<CheckUserResponse>
        get() = _checkUser

    private var _register: MutableLiveData<RegisterResponse> = MutableLiveData()
    val register: LiveData<RegisterResponse>
        get() = _register

    private var _checkUserError: MutableLiveData<RegisterCheckUserResponse> = MutableLiveData()
    val checkUserError: LiveData<RegisterCheckUserResponse>
        get() = _checkUserError

    private var _registerError: MutableLiveData<Boolean> = MutableLiveData()
    val registerError: LiveData<Boolean>
        get() = _registerError

    fun checkUser(email: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.checkUser(CheckUser(email.lowercase()))

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        _checkUser.postValue(response.body())
                    } else if (response.code() == 400) {
                        try {
                            val gson = Gson()
                            val errorResponse = gson.fromJson(
                                response.errorBody()?.string(),
                                RegisterCheckUserResponse::class.java
                            )

                            _checkUserError.postValue(errorResponse)
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

    fun register(
        email: String,
        password: String,
        rePassword: String,
        agreement: String
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response =
                    repository.register(Register(email, password, rePassword, agreement))

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        _register.postValue(response.body())
                    } else if (response.code() == 400) {
                        _registerError.postValue(true)
                    } else {
                        response.errorBody()?.string()?.let { Log.e("AUTH", it) }
                    }
                }
            }
        }
    }
}