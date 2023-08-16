package ru.partyshaker.partyshaker.ui.features.cocktails_filter.data.data_classes

import com.google.gson.annotations.SerializedName
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.Complexity

data class ComplexitiesResponse(
    @SerializedName("results")
    val complexities: List<Complexity>
)