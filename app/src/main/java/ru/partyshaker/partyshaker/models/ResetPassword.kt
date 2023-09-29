package ru.partyshaker.partyshaker.models

import com.google.gson.annotations.SerializedName

data class ResetPassword(
    @SerializedName("email")
    val email: String
)