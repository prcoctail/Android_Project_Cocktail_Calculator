package ru.partyshaker.partyshaker.data.partyShaker.users.impl

import androidx.lifecycle.MutableLiveData
import ru.partyshaker.partyshaker.data.*
import ru.partyshaker.partyshaker.data.network.NetworkApi
import ru.partyshaker.partyshaker.data.partyShaker.BaseApi
import ru.partyshaker.partyshaker.data.partyShaker.users.User
import ru.partyshaker.partyshaker.data.partyShaker.users.UsersRepository
import ru.partyshaker.partyshaker.data.partyShaker.users.config.UserConfig
import ru.partyshaker.partyshaker.data.partyShaker.users.impl.model.AuthToken
import ru.partyshaker.partyshaker.data.partyShaker.users.impl.model.NewUser
import ru.partyshaker.partyshaker.data.partyShaker.users.impl.model.UserLoginRequest
import ru.partyshaker.partyshaker.data.partyShaker.users.impl.model.UsersListResponse
import javax.inject.Inject
import javax.inject.Singleton

// Регистрация,токен,логин, пользователи, кеш пользователья

@Singleton
class UsersRepositoryImpl
@Inject constructor(
    networkApi: NetworkApi,
    private val userConfig: UserConfig,
) : UsersRepository {

    private var authCancelled = false

    private val api: UsersApi =
        networkApi.createApi(BaseApi.BASE_URL, UsersApi::class.java)

    override suspend fun getAllUsers(limit: Int, page: Int): ResultT<UsersListResponse> {
        return api.getAllUsers(limit, page).result()
    }

    var authing = MutableLiveData(false)

    override suspend fun registerUser(user: NewUser): ResultT<User> {
        if (authing.value == true) return ResultT.Failure(emptyError)

        authing.postValue(true)
        val response = api.registerUser(user)
        authing.postValue(false)

        return if (response.success()) {
            //Сохранить пользователь в кеш
            val userR = response.body()!!
            userConfig.saveUser(userR)
            ResultT.Success(userR)
        } else {
            ResultT.Failure(response.error())
        }
    }

    override suspend fun user(id: Long): ResultT<User> {
        return api.user(id).result()
    }

    override suspend fun authToken(email: String, password: String): ResultT<AuthToken> {
        return api.authToken(UserLoginRequest(password, email)).result()
    }

    override fun currentUser(): User? {
        return UserConfig.user
    }

    override fun userLogged(): Boolean {
        return UserConfig.logged()
    }

    override fun cancelAuth() {
        authCancelled = true
    }
}