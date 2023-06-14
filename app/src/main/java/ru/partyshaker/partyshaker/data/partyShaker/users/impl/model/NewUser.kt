package ru.partyshaker.partyshaker.data.partyShaker.users.impl.model

import com.google.gson.annotations.SerializedName

data class NewUser(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("re_password") val rePassword: String,
    @SerializedName("agreement") val agreement: String,
    @SerializedName("username") val username: String
)