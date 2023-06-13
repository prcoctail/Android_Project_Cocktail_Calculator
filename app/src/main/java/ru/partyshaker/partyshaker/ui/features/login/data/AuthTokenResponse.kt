package ru.partyshaker.partyshaker.ui.features.login.data

import com.google.gson.annotations.SerializedName

data class AuthTokenResponse(
    @SerializedName("auth_token")
    var authToken: String
)