package com.example.roomloginapp.data

import android.content.Context
import android.content.SharedPreferences

/**
 * SharedPreferences manager for user registered status
 */
class SharedPreferencesManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("AUTH_INFO", 0)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    // Set user registered status
    fun setRegistered(registered: Boolean) {
        editor.putBoolean("REGISTERED", registered)
        editor.apply()
    }

    // Get user registered status
    fun checkRegistered(): Boolean = sharedPreferences.getBoolean("REGISTERED", false)

}