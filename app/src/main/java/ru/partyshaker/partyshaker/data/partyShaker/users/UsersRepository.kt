package ru.partyshaker.partyshaker.data.partyShaker.users

import ru.partyshaker.partyshaker.data.errorMessage
import ru.partyshaker.partyshaker.data.network.NetworkApi
import ru.partyshaker.partyshaker.data.partyShaker.BaseApi
import ru.partyshaker.partyshaker.data.partyShaker.users.model.UsersListResponse
import javax.inject.Inject

class UsersRepository(@Inject private val networkApi: NetworkApi) {

    companion object {
        const val FETCH_LIMIT = 12
    }

    private val api: UsersApi = networkApi.createApi(BaseApi.BASE_URL, UsersApi::class.java)

    suspend fun getAllUsers(
        limit: Int = FETCH_LIMIT,
        page: Int = 1
    ): Result<UsersListResponse> {

        val result = api.getAllUsers(limit, page)

        return if (result.body() != null && result.isSuccessful && result.errorBody() == null) {
            //Successfully got the list
            Result.success(result.body()!!)
        } else {
            Result.failure(Throwable(result.errorMessage()))
        }
    }
}