package ru.partyshaker.partyshaker.data.responses.auth

import com.google.gson.annotations.SerializedName

data class PassRestoreErrorResponse(
    @SerializedName("error")
    val error: String
)