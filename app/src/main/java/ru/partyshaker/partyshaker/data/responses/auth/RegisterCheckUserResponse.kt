package ru.partyshaker.partyshaker.data.responses.auth

import com.google.gson.annotations.SerializedName

data class RegisterCheckUserResponse(
    @SerializedName("email")
    val email: String
)