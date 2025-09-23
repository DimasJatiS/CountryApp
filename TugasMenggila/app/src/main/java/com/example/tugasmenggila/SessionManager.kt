package com.example.tugasmenggila

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {


    // Mengambil sesi jika sudah login atau belum ke aplikasi
    private val prefs: SharedPreferences = context.getSharedPreferences("MyAppSession", Context.MODE_PRIVATE)

    fun setLoggedIn(loggedIn: Boolean) {
        prefs.edit().putBoolean("isLoggedIn", loggedIn).apply()
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean("isLoggedIn", false)
    }

    //jika logout maka data dihapus
    fun logout() {
        prefs.edit().clear().apply()
    }
}