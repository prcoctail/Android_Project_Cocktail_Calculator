package ru.partyshaker.partyshaker.data.partyShaker.users

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.partyshaker.partyshaker.data.partyShaker.users.model.UsersListResponse

interface UsersApi {

    @GET("users")
    suspend fun getAllUsers(
        @Query("limit")
        limit: Int,
        @Query("page")
        page: Int
    ): Response<UsersListResponse>
}