package ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.filter

import com.google.gson.annotations.SerializedName

data class FilteredChip(
    @SerializedName("name")
    var name: String,
    @SerializedName("isChecked")
    var isChecked: Boolean
) {
}