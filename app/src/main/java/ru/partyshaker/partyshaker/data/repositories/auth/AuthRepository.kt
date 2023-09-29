package ru.partyshaker.partyshaker.data.repositories.auth

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import ru.partyshaker.partyshaker.data.responses.auth.CheckUserResponse
import ru.partyshaker.partyshaker.data.responses.auth.LoginResponse
import ru.partyshaker.partyshaker.data.responses.auth.RegisterResponse
import ru.partyshaker.partyshaker.di.AuthService
import ru.partyshaker.partyshaker.models.CheckLogin
import ru.partyshaker.partyshaker.models.CheckUser
import ru.partyshaker.partyshaker.models.Register
import ru.partyshaker.partyshaker.models.ResendActivation
import ru.partyshaker.partyshaker.models.ResetPassword
import javax.inject.Inject


class AuthRepository @Inject constructor(
    private val service: AuthService
) {
    suspend fun checkUser(checkUser: CheckUser): Response<CheckUserResponse> {
        return withContext(Dispatchers.IO) {
            service.checkUser(checkUser)
        }
    }

    suspend fun register(register: Register): Response<RegisterResponse> {
        return withContext(Dispatchers.IO) {
            service.register(register)
        }
    }

    suspend fun checkLogin(checkLogin: CheckLogin): Response<LoginResponse> {
        return withContext(Dispatchers.IO) {
            service.checkLogin(checkLogin)
        }
    }

    suspend fun resetPassword(resetPassword: ResetPassword): Response<Void> {
        return withContext(Dispatchers.IO) {
            service.resetPassword(resetPassword)
        }
    }

    suspend fun resendActivation(resendActivation: ResendActivation): Response<Void> {
        return withContext(Dispatchers.IO) {
            service.resendActivation(resendActivation)
        }
    }
}