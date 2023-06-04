package ru.partyshaker.partyshaker.features.login.domain

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ru.partyshaker.partyshaker.features.login.data.AuthTokenResponse

interface AuthorizationService {
    @POST("/api/auth/token/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<AuthTokenResponse>
}


data class LoginRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
)