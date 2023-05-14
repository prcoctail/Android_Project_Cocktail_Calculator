package ru.partyshaker.partyshaker.cocktails.data.data_classes

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("author")
    val author: String,
    @SerializedName("complexity")
//    val complexity: List<Complexity>,
//    @SerializedName("description")
    val description: String,
    @SerializedName("format")
//    val format: List<Format>,
//    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("ingredients")
//    val ingredients: List<Ingredient>,
//    @SerializedName("is_favorited")
    val isFavorited: Boolean,
    @SerializedName("name")
    val name: String,
//    @SerializedName("strength")
//    val strength: List<Strength>,
//    @SerializedName("taste")
//    val taste: List<Taste>
)