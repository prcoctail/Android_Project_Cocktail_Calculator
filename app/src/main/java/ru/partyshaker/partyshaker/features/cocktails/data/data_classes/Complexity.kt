package ru.partyshaker.partyshaker.ApiCocktails


import com.google.gson.annotations.SerializedName

data class Complexity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String
)