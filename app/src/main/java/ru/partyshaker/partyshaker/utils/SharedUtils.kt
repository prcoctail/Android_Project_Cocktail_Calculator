package ru.partyshaker.partyshaker.utils

import android.content.Context
import android.content.SharedPreferences

object SharedUtils {

    fun sharedPrefs(
        context: Context,
        name: String,
        mode: Int = Context.MODE_PRIVATE
    ): SharedPreferences = (context.getSharedPreferences(name, mode))

}