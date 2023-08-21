package ru.partyshaker.partyshaker.models

import com.google.gson.annotations.SerializedName

data class CheckUser(
    @SerializedName("email")
    val email: String
)