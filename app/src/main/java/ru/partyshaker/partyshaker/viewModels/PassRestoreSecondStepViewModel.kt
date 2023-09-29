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
internal class PassRestoreSecondStepViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private var _passRestoreError: MutableLiveData<PassRestoreErrorResponse> = MutableLiveData()
    val passRestoreError: LiveData<PassRestoreErrorResponse>
        get() = _passRestoreError

    fun passRestore(email: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.resetPassword(ResetPassword(email.lowercase()))

                withContext(Dispatchers.Main) {
                    if (response.code() == 400
                        || response.code() == 403
                    ) {
                        try {
                            val gson = Gson()
                            val loginErrorResponse = gson.fromJson(
                                response.errorBody()?.string(),
                                PassRestoreErrorResponse::class.java
                            )

                            _passRestoreError.postValue(loginErrorResponse)
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