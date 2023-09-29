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
import ru.partyshaker.partyshaker.data.responses.auth.PassRestoreErrorResponse
import ru.partyshaker.partyshaker.models.ResetPassword
import javax.inject.Inject

@HiltViewModel
internal class PassRestoreFirstStepViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private var _email: MutableLiveData<Boolean> = MutableLiveData()
    val email: LiveData<Boolean>
        get() = _email

    private var _emailError: MutableLiveData<PassRestoreErrorResponse> = MutableLiveData()
    val emailError: LiveData<PassRestoreErrorResponse>
        get() = _emailError

    fun clearEmail() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                _email.postValue(false)
            }
        }
    }

    fun resetPassword(email: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.resetPassword(ResetPassword(email.lowercase()))

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        _email.postValue(true)
                    } else if (response.code() == 400
                        || response.code() == 403
                    ) {
                        try {
                            val gson = Gson()
                            val loginErrorResponse = gson.fromJson(
                                response.errorBody()?.string(),
                                PassRestoreErrorResponse::class.java
                            )

                            _emailError.postValue(loginErrorResponse)
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