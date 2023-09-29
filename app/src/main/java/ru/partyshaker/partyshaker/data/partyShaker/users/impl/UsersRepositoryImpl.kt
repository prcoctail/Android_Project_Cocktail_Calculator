package ru.partyshaker.partyshaker.data.partyShaker.users.impl

import androidx.lifecycle.MutableLiveData
import ru.partyshaker.partyshaker.data.error
import ru.partyshaker.partyshaker.data.network.NetworkApi
import ru.partyshaker.partyshaker.data.partyShaker.BaseApi
import ru.partyshaker.partyshaker.data.partyShaker.users.User
import ru.partyshaker.partyshaker.data.partyShaker.users.impl.model.AuthToken
import ru.partyshaker.partyshaker.data.partyShaker.users.impl.model.NewUser
import ru.partyshaker.partyshaker.data.partyShaker.users.impl.model.UserLoginRequest
import ru.partyshaker.partyshaker.data.partyShaker.users.impl.model.UsersListResponse
import ru.partyshaker.partyshaker.data.result
import ru.partyshaker.partyshaker.data.success
import javax.inject.Inject
import javax.inject.Singleton

// Регистрация,токен,логин, пользователи, кеш пользователья

//@Singleton
//class UsersRepositoryImpl
//@Inject constructor(
//    networkApi: NetworkApi,
//    private val userConfig: UserConfig,
//) : UsersRepository {
//
//    private var authCancelled = false
//
//    private val api: UsersApi =
//        networkApi.createApi(BaseApi.BASE_URL, UsersApi::class.java)
//
//    override suspend fun getAllUsers(limit: Int, page: Int): ru.partyshaker.partyshaker.data.ResultT<UsersListResponse> {
//        return api.getAllUsers(limit, page).result()
//    }
//
//    var authing = MutableLiveData(false)
//
//    override suspend fun registerUser(user: NewUser): ru.partyshaker.partyshaker.data.ResultT<User> {
//        if (authing.value == true) return ru.partyshaker.partyshaker.data.ResultT.Failure(ru.partyshaker.partyshaker.data.emptyError)
//
//        authing.postValue(true)
//        val response = api.registerUser(user)
//        authing.postValue(false)
//
//        return if (response.success()) {
//            //Сохранить пользователь в кеш
//            val userR = response.body()!!
//            userConfig.saveUser(userR)
//            ru.partyshaker.partyshaker.data.ResultT.Success(userR)
//        } else {
//            ru.partyshaker.partyshaker.data.ResultT.Failure(response.error())
//        }
//    }
//
//    override suspend fun user(id: Long): ru.partyshaker.partyshaker.data.ResultT<User> {
//        return api.user(id).result()
//    }
//
//    override suspend fun authToken(email: String, password: String): ru.partyshaker.partyshaker.data.ResultT<AuthToken> {
//        return api.authToken(UserLoginRequest(password, email)).result()
//    }
//
//    override fun currentUser(): User? {
//        return UserConfig.user
//    }
//
//    override fun userLogged(): Boolean {
//        return UserConfig.logged()
//    }
//
//    override fun cancelAuth() {
//        authCancelled = true
//    }
//}