package ru.partyshaker.partyshaker.data.partyShaker.users.model

data class UsersListResponse(
    val count: Int,
    val next: Int,
    val previous: Int,
    val results: List<User>?
)