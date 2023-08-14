package com.mora278.aptivist_protodatastore.data.preferencesdatastore

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    val NAME = stringPreferencesKey("name")
    val LAST_NAME = stringPreferencesKey("last_name")
    val E_MAIL = stringPreferencesKey("e_mail")
    val AGE = stringPreferencesKey("age")
    val DESCRIPTION = stringPreferencesKey("description")
    val newInt = intPreferencesKey("new_int")
}