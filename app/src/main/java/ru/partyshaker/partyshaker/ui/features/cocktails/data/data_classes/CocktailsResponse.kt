package ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes

import com.google.gson.annotations.SerializedName

data class CocktailsResponse(
//    @SerializedName("count")
//    val count: Int,
//    @SerializedName("next")
//    val next: String?,
//    @SerializedName("previous")
//    val previous: String?,
    @SerializedName("results")
    val cocktails: List<Cocktail>
)