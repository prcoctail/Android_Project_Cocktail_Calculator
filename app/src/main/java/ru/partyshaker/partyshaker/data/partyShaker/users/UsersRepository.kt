package ru.partyshaker.partyshaker.data.partyShaker.users

import ru.partyshaker.partyshaker.data.ResultT
import ru.partyshaker.partyshaker.data.partyShaker.users.impl.model.AuthToken
import ru.partyshaker.partyshaker.data.partyShaker.users.impl.model.NewUser
import ru.partyshaker.partyshaker.data.partyShaker.users.impl.model.UsersListResponse

//interface UsersRepository {
//
//    companion object {
//        const val FETCH_LIMIT = 12
//    }
//
//    suspend fun getAllUsers(
//        limit: Int = FETCH_LIMIT,
//        page: Int = 1
//    ): ResultT<UsersListResponse>
//
//
//    suspend fun registerUser(user: NewUser): ResultT<User>
//
//    suspend fun user(id: Long): ResultT<User>
//
//    suspend fun authToken(email: String, password: String): ResultT<AuthToken>
//
//    fun userLogged(): Boolean
//
//    fun currentUser(): User?
//
//    fun cancelAuth()
//
//}
