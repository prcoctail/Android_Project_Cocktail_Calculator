package ru.partyshaker.partyshaker.features.cocktails.data.data_classes

import com.google.gson.annotations.SerializedName

data class Author(
    @SerializedName("username")
    val username: String
)
