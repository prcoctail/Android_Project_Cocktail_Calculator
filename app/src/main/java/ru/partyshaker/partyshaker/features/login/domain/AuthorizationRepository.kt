package ru.partyshaker.partyshaker.features.login.domain

import javax.inject.Inject
import ru.partyshaker.partyshaker.feature_utils.entity.Result

class AuthorizationRepository @Inject constructor(
    private val retrofitService: AuthorizationService
) {
    suspend fun login(loginRequest: LoginRequest): Result<String> {
        val tokenResponse = retrofitService.login(loginRequest)

        return if (tokenResponse.isSuccessful){
            Result.Success(tokenResponse.body()?.authToken?:"")
        }else{
            Result.Error(tokenResponse.message())
        }
    }
}