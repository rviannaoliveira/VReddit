package com.rviannaoliveira.vreddit.data.repository

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.rviannaoliveira.vreddit.AppApplication

object RedditSharedPreference {

    private val prefs: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(AppApplication.appContext)

    fun insert(key: String, value: String?) {
        value?.let {
            prefs.edit().putString(key, value).apply()
        }
    }

    fun getValue(key: String, _default: String = ""): String {
        return prefs.getString(key, _default)
    }
}
