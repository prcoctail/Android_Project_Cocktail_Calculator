package ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.filter

import com.google.gson.annotations.SerializedName
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.Strength

data class StrengthsResponse(
    @SerializedName("results")
    val strengths: List<Strength>
)