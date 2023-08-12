package ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes


import com.google.gson.annotations.SerializedName


data class Taste(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String
)