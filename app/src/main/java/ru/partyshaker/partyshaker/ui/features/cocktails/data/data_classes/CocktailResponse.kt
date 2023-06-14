package ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes

import com.google.gson.annotations.SerializedName

data class CocktailResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("results")
    val cocktails: List<ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.Cocktail>,
)