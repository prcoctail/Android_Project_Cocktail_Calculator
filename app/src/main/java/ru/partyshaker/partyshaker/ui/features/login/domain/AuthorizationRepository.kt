package ru.partyshaker.partyshaker.ui.features.login.domain

import com.google.gson.Gson

import javax.inject.Inject

class AuthorizationRepository @Inject constructor(
    private val retrofitService: ru.partyshaker.partyshaker.ui.features.login.domain.AuthorizationService
) {
    private val gson = Gson()
    suspend fun login(loginRequest: ru.partyshaker.partyshaker.ui.features.login.domain.LoginRequest): Result<String> {
        val tokenResponse = retrofitService.login(loginRequest)

       return Result.failure(Throwable())
    }

    private fun parseErrorMessage(json: String?): String {
        return try {
            val errorResponse = gson.fromJson(json, ru.partyshaker.partyshaker.ui.features.login.data.ErrorResponse::class.java)
            errorResponse?.error ?: ru.partyshaker.partyshaker.ui.features.login.domain.AuthorizationRepository.Companion.UNKNOWN_ERROR
        } catch (e: Exception) {
            ru.partyshaker.partyshaker.ui.features.login.domain.AuthorizationRepository.Companion.UNKNOWN_ERROR
        }
    }

    companion object {
        private const val UNKNOWN_ERROR = "Неизвестная ошибка"
    }
}