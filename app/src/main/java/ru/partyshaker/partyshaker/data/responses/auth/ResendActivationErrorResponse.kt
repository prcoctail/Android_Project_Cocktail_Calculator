package ru.partyshaker.partyshaker.data.responses.auth

import com.google.gson.annotations.SerializedName

data class ResendActivationErrorResponse(
    @SerializedName("error")
    val error: String
)