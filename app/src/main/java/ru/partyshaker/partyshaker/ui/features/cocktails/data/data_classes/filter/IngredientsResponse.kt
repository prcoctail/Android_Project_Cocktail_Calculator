package ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.filter

import com.google.gson.annotations.SerializedName
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.Ingredient

data class IngredientsResponse(
    @SerializedName("results")
    val ingredients: List<Ingredient>
)