package ru.partyshaker.partyshaker.features.login.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.partyshaker.partyshaker.feature_utils.entity.Result
import ru.partyshaker.partyshaker.features.login.domain.AuthorizationRepository
import ru.partyshaker.partyshaker.features.login.domain.LoginRequest
import ru.partyshaker.partyshaker.features.login.domain.SessionManager
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: AuthorizationRepository, private val sessionManager: SessionManager ) :
    ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun getAuthToken(loginRequest: LoginRequest) {
        viewModelScope.launch {
            try {
                when (val tokenResult = repository.login(loginRequest)) {
                    is Result.Success -> {
                        if (tokenResult.data.isNotEmpty()) {
                            sessionManager.saveAuthToken(tokenResult.data)
                            println("YOUR TOKEN IS: ${tokenResult.data}")
                        } else {
                            Log.e(TOKEN_EXCEPTION_TAG, EMPTY_TOKEN_MESSAGE)
                        }
                    }
                    is Result.Error -> {
                        _errorMessage.postValue(tokenResult.exception)
                        Log.e(TOKEN_EXCEPTION_TAG, tokenResult.exception)
                    }
                }
            } catch (e: Exception) {
                _errorMessage.postValue(NETWORK_ERROR_MESSAGE)
                Log.e(LOGIN_EXCEPTION_TAG, e.toString())
            }
        }
    }

    companion object {
        private const val TOKEN_EXCEPTION_TAG = "Token Exception: "
        private const val LOGIN_EXCEPTION_TAG = "Login Exception: "
        private const val EMPTY_TOKEN_MESSAGE = "ERROR LOGGING IN: Token is empty"
        private const val NETWORK_ERROR_MESSAGE = "Проверьте соединение"
    }
}