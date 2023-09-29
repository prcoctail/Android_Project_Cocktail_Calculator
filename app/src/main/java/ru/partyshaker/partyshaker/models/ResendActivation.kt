package ru.partyshaker.partyshaker.models

import com.google.gson.annotations.SerializedName

data class ResendActivation(
    @SerializedName("email")
    val email: String
)