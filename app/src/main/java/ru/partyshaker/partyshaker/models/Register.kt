package ru.partyshaker.partyshaker.models

import com.google.gson.annotations.SerializedName

data class Register(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("re_password")
    val rePassword: String,
    @SerializedName("agreement")
    val agreement: String
)