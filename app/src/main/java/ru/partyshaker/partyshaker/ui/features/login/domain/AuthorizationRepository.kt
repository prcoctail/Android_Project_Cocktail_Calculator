package ru.partyshaker.partyshaker.ui.features.login.domain

import com.google.gson.Gson
import ru.partyshaker.partyshaker.ui.features.login.data.ErrorResponse

import javax.inject.Inject

class AuthorizationRepository @Inject constructor(
    private val retrofitService: AuthorizationService
) {
    private val gson = Gson()
    suspend fun login(loginRequest: LoginRequest): Result<String> {
        val tokenResponse = retrofitService.login(loginRequest)

       return Result.failure(Throwable())
    }

    private fun parseErrorMessage(json: String?): String {
        return try {
            val errorResponse = gson.fromJson(json, ErrorResponse::class.java)
            errorResponse?.error ?: UNKNOWN_ERROR
        } catch (e: Exception) {
            UNKNOWN_ERROR
        }
    }

    companion object {
        private const val UNKNOWN_ERROR = "Неизвестная ошибка"
    }
}