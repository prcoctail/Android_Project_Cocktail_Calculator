package ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes

import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_name")
    val imageName: String,
    @SerializedName("big_image")
    val bigImage: String,
    @SerializedName("medium_image")
    val mediumImage: String,
    @SerializedName("small_image")
    val smallImage: String
)