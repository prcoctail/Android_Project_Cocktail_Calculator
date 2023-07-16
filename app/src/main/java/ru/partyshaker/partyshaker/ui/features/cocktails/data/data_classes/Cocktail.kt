package ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes

import com.google.gson.annotations.SerializedName

data class Cocktail(
    @SerializedName("id")
    val id: Int,
    @SerializedName("author")
    val author: Author,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("complexities")
    val complexities: List<Complexity>?,
    @SerializedName("tastes")
    val tastes: List<Taste>?,
    @SerializedName("formats")
    val formats: List<Format>?,
    @SerializedName("strengths")
    val strengths: List<Strength>?,
    @SerializedName("images")
    val images: List<Images>?,
    @SerializedName("ingredients")
    val ingredients: List<Ingredient>?,
    @SerializedName("is_favorited")
    val isFavorited: Boolean
)