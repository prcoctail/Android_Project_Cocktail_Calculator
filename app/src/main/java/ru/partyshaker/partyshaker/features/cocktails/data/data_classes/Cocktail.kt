package ru.partyshaker.partyshaker.features.cocktails.data.data_classes

import com.google.gson.annotations.SerializedName
import ru.partyshaker.partyshaker.ApiCocktails.*

data class Cocktail(
    @SerializedName("author")
    val author: Author,
    @SerializedName("complexity")
    val complexity: List<Complexity>,
    @SerializedName("description")
    val description: String,
    @SerializedName("format")
    val format: List<Format>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: List<Images>,
    @SerializedName("ingredients")
    val ingredients: List<Ingredient>,
    @SerializedName("is_favorited")
    val isFavorite: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("strength")
    val strength: List<Strength>,
    @SerializedName("taste")
    val taste: List<Taste>
    )