package ru.partyshaker.partyshaker.features.login.domain

import com.google.gson.Gson
import javax.inject.Inject
import ru.partyshaker.partyshaker.feature_utils.entity.Result
import ru.partyshaker.partyshaker.features.login.data.ErrorResponse

class AuthorizationRepository @Inject constructor(
    private val retrofitService: AuthorizationService
) {
    private val gson = Gson()
    suspend fun login(loginRequest: LoginRequest): Result<String> {
        val tokenResponse = retrofitService.login(loginRequest)

        return if (tokenResponse.isSuccessful){
            Result.Success(tokenResponse.body()?.authToken?:"")
        }else{
            val errorMessage = parseErrorMessage(tokenResponse.errorBody()?.string())
            Result.Error(errorMessage)
        }
    }

    private fun parseErrorMessage(json: String?): String{
        return try {
            val errorResponse = gson.fromJson(json, ErrorResponse::class.java)
            errorResponse?.error?: UNKNOWN_ERROR
        }catch (e: Exception){
            UNKNOWN_ERROR
        }
    }

    companion object{
        private const val UNKNOWN_ERROR = "Неизвестная ошибка"
    }
}