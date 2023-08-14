package com.mora278.aptivist_protodatastore.domain.services

interface ISharedPreferences {
    fun getValue(key: String, defaultValue: String): String
    fun getValue(key: String, defaultValue: Boolean): Boolean
    fun getValue(key: String, defaultValue: Int): Int
    fun putValue(key: String, value: String)
    fun putValue(key: String, value: Boolean)
    fun putValue(key: String, value: Int)
}