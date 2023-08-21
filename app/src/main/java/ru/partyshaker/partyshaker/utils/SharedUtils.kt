package ru.partyshaker.partyshaker.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

class SharedUtils {

    companion object {
        private const val KEY_REFRESH = "refresh"
        private const val KEY_ACCESS = "access"
    }

    private lateinit var prefs: SharedPreferences
    private lateinit var editor: Editor

    fun setSharedPrefs(
        context: Context,
        name: String,
        mode: Int = Context.MODE_PRIVATE
    ) {
        prefs = context.getSharedPreferences(name, mode)
        editor = prefs.edit()
    }

    fun setToken(refresh: String, access: String) {
        editor.putString(KEY_REFRESH, refresh)
        editor.putString(KEY_ACCESS, access)
        editor.commit()
    }

    fun getRefresh(): String? {
        return prefs.getString(KEY_REFRESH, null)
    }

    fun getAccess(): String? {
        return prefs.getString(KEY_ACCESS, null)
    }
}