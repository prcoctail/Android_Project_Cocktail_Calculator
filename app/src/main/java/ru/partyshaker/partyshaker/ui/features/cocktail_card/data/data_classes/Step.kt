package ru.partyshaker.partyshaker.ui.features.cocktail_card.data.data_classes

import com.google.gson.annotations.SerializedName

data class Step(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String
)