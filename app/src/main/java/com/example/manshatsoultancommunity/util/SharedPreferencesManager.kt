package com.example.manshatsoultancommunity.util

import android.content.Context
import android.content.SharedPreferences
import com.google.common.reflect.TypeToken
import com.google.gson.Gson

class SharedPreferencesManager (context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(Constants.SHARD_KEY, Context.MODE_PRIVATE)

    public fun saveString(key: String, value: String) {
        sharedPreferences.edit()
            .putString(key, value)
            .apply()
    }
    fun saveList(key: String,myList: List<String>) {
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(myList)
        editor.putString(key,json)
        editor.apply()
    }
    fun getList(key: String): List<String> {
        val gson = Gson()
        val json = sharedPreferences.getString(key, "")
        if (json.isNullOrEmpty()) {

            return emptyList()
        }
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(json, type)
    }

    public fun saveBoolean(key: String, value: Boolean) {
        sharedPreferences.edit()
            .putBoolean(key, value)
            .apply()
    }
    public fun saveInt(key: String, value: Int) {
        sharedPreferences.edit()
            .putInt(key, value)
            .apply()
    }
    public fun getString(key: String, defaultValue: String = ""): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    public fun getInt(key: String, defaultValue: Int = 1): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    public fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue) ?: defaultValue
    }
    public fun clearItem(key: String) {
        sharedPreferences.edit()
            .remove(key)
            .apply()
    }



}