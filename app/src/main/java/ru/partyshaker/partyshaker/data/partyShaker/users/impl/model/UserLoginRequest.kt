package ru.partyshaker.partyshaker.data.partyShaker.users.impl.model

import com.google.gson.annotations.SerializedName

data class UserLoginRequest(
    @SerializedName("password") val password: String,
    @SerializedName("email") val email: String
)