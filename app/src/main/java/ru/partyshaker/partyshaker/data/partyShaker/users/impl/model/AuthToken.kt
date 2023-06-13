package ru.partyshaker.partyshaker.data.partyShaker.users.impl.model

import com.google.gson.annotations.SerializedName

data class AuthToken(
    @SerializedName("auth_token")
    val token: String
)