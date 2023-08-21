package ru.partyshaker.partyshaker.data.responses.auth

import com.google.gson.annotations.SerializedName

data class AuthTokenResponse(
    @SerializedName("auth_token")
    var authToken: String
)