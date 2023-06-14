package ru.partyshaker.partyshaker.ui.features.login.data

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("error")
    val error: String
    )