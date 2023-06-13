package ru.partyshaker.partyshaker.data.partyShaker.users.impl

import retrofit2.Response
import retrofit2.http.*
import ru.partyshaker.partyshaker.data.partyShaker.users.User
import ru.partyshaker.partyshaker.data.partyShaker.users.impl.model.AuthToken
import ru.partyshaker.partyshaker.data.partyShaker.users.impl.model.NewUser
import ru.partyshaker.partyshaker.data.partyShaker.users.impl.model.UserLoginRequest
import ru.partyshaker.partyshaker.data.partyShaker.users.impl.model.UsersListResponse

interface UsersApi {

    @GET("users")
    suspend fun getAllUsers(
        @Query("limit")
        limit: Int,
        @Query("page")
        page: Int
    ): Response<UsersListResponse>

    @POST("users")
    suspend fun registerUser(
        @Body
        user: NewUser
    ): Response<User>

    @GET("users/{id}")
    suspend fun user(
        @Path("id") id: Long
    ): Response<User>

    @POST("auth/token/login")
    suspend fun authToken(@Body userReq: UserLoginRequest): Response<AuthToken>
}