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
import ru.partyshaker.partyshaker.data.responses.auth.ResendActivationErrorResponse
import ru.partyshaker.partyshaker.models.CheckLogin
import ru.partyshaker.partyshaker.models.ResendActivation
import javax.inject.Inject

@HiltViewModel
class AccActivationViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private var _endActivation: MutableLiveData<Boolean> = MutableLiveData()
    val endActivation: LiveData<Boolean>
        get() = _endActivation

    private var _resendActivationError: MutableLiveData<ResendActivationErrorResponse> =
        MutableLiveData()
    val resendActivationError: LiveData<ResendActivationErrorResponse>
        get() = _resendActivationError


    fun checkLogin(email: String, pass: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.checkLogin(CheckLogin(email, pass))

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        _endActivation.postValue(true)
                    } else if (response.code() == 403) {
                        resendActivation(email)
                    } else {
                        response.errorBody()?.string()?.let { Log.e("AUTH", it) }
                    }
                }
            }
        }
    }

    private fun resendActivation(email: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response =
                    repository.resendActivation(ResendActivation(email))

                withContext(Dispatchers.Main) {
                    if (response.code() == 400) {
                        try {
                            val gson = Gson()
                            val errorResponse = gson.fromJson(
                                response.errorBody()?.string(),
                                ResendActivationErrorResponse::class.java
                            )

                            _resendActivationError.postValue(errorResponse)
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