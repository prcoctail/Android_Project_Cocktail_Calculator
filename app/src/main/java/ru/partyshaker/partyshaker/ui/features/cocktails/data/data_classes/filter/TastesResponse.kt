package ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.filter

import com.google.gson.annotations.SerializedName
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.Taste

data class TastesResponse(
    @SerializedName("results")
    val tastes: List<Taste>
)