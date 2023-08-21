package ru.partyshaker.partyshaker.di

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ru.partyshaker.partyshaker.data.responses.auth.LoginResponse
import ru.partyshaker.partyshaker.data.responses.auth.CheckUserResponse
import ru.partyshaker.partyshaker.data.responses.auth.RegisterResponse
import ru.partyshaker.partyshaker.models.ResetPassword
import ru.partyshaker.partyshaker.models.CheckLogin
import ru.partyshaker.partyshaker.models.CheckUser
import ru.partyshaker.partyshaker.models.Register
import ru.partyshaker.partyshaker.models.ResendActivation

interface AuthService {
    @POST("users/")
    suspend fun register(
        @Body register: Register
    ): Response<RegisterResponse>

    @POST("users/reset_password/")
    suspend fun resetPassword(
        @Body resetPassword: ResetPassword
    ): Response<Void>

    @POST("users/resend_activation/")
    suspend fun resendActivation(
        @Body resendActivation: ResendActivation
    ): Response<Void>

    @POST("user/check-user/")
    suspend fun checkUser(
        @Body checkUser: CheckUser
    ): Response<CheckUserResponse>

    @POST("auth/jwt/create/")
    suspend fun checkLogin(
        @Body checkLogin: CheckLogin
    ): Response<LoginResponse>

//    @POST("/api/auth/token/login")
//    suspend fun login(
//        @Body loginResponse: LoginResponse
//    ): Response<AuthTokenResponse>
}


