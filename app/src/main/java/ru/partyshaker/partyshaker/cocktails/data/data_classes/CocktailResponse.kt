package ru.partyshaker.partyshaker.cocktails.data.data_classes

import com.google.gson.annotations.SerializedName

data class CocktailResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: String,
    @SerializedName("results")
    val results: List<Result>
)