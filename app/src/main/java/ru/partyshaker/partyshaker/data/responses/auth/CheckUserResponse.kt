package ru.partyshaker.partyshaker.data.responses.auth

import com.google.gson.annotations.SerializedName

data class CheckUserResponse(
    @SerializedName("check_user")
    val checkUser: String
)