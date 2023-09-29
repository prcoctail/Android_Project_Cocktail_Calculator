package ru.partyshaker.partyshaker.data.responses.auth

import com.google.gson.annotations.SerializedName

data class LoginErrorResponse(
    @SerializedName("error")
    val error: String
)