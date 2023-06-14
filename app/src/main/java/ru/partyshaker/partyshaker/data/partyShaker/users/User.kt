package ru.partyshaker.partyshaker.data.partyShaker.users

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email") var email: String = "",
    @SerializedName("username") var userName: String = "",
    @SerializedName("first_name") var firstName: String = "",
    @SerializedName("last_name") var lastName: String = "",
    @SerializedName("id") var id: Long = 0,
    @SerializedName("profile_picture") var profilePicture: String = ""
)