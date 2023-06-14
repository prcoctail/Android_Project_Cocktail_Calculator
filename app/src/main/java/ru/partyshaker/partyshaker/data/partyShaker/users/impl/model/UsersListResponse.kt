package ru.partyshaker.partyshaker.data.partyShaker.users.impl.model

import ru.partyshaker.partyshaker.data.partyShaker.users.User

data class UsersListResponse(
    val count: Int, val next: Int, val previous: Int, val results: List<User>?
)