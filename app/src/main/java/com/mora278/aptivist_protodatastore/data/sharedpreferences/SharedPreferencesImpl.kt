package com.mora278.aptivist_protodatastore.data.sharedpreferences

import android.content.SharedPreferences
import androidx.core.content.edit
import com.mora278.aptivist_protodatastore.domain.services.ISharedPreferences

class SharedPreferencesImpl(
    private val sharedPreferences: SharedPreferences
) : ISharedPreferences {
    override fun getValue(key: String, defaultValue: String): String =
        sharedPreferences.getString(key, defaultValue) ?: defaultValue

    override fun getValue(key: String, defaultValue: Boolean): Boolean =
        sharedPreferences.getBoolean(key, defaultValue)

    override fun getValue(key: String, defaultValue: Int): Int =
        sharedPreferences.getInt(key, defaultValue)

    override fun putValue(key: String, value: String) {
        sharedPreferences
            .edit()
            .putString(key, value)
            .apply()
    }

    override fun putValue(key: String, value: Boolean) {
        sharedPreferences
            .edit()
            .putBoolean(key, value)
            .apply()
    }

    override fun putValue(key: String, value: Int) {
        sharedPreferences
            .edit()
            .putInt(key, value)
            .apply()
    }
}