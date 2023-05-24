package ru.partyshaker.partyshaker.features.login.data

import com.google.gson.annotations.SerializedName

data class AuthTokenResponse(
    @SerializedName("auth_token")
    var authToken: String
)