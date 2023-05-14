package ru.partyshaker.partyshaker.ApiCocktails


import com.google.gson.annotations.SerializedName


data class Format(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String
)