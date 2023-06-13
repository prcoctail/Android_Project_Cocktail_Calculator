package ru.partyshaker.partyshaker.ui.features.login.domain

import android.content.SharedPreferences
import javax.inject.Inject

class SessionManager @Inject constructor(private val sharedPref: SharedPreferences) {

    companion object {
        const val AUTH_TOKEN = "auth_token"
    }

    fun saveAuthToken(token: String) {
        val editor = sharedPref.edit()
        editor.putString(AUTH_TOKEN, token)
        editor.apply()
    }

    fun getAuthToken(): String? {
        return sharedPref.getString(AUTH_TOKEN, null)
    }
}