package ru.partyshaker.partyshaker.models

import com.google.gson.annotations.SerializedName

data class CheckLogin(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)