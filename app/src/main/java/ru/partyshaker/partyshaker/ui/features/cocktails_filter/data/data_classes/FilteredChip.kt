package ru.partyshaker.partyshaker.ui.features.cocktails_filter.data.data_classes

import com.google.gson.annotations.SerializedName

data class FilteredChip(
    @SerializedName("name")
    var name: String,
    @SerializedName("isChecked")
    var isChecked: Boolean
) {
}