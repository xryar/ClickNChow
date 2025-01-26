package com.example.clicknchow

import android.content.SharedPreferences
import androidx.multidex.MultiDexApplication
import androidx.preference.PreferenceManager
import com.example.clicknchow.network.HttpClient

class ClickNChow: MultiDexApplication() {

    companion object {
        lateinit var instance: ClickNChow

        fun getApp() : ClickNChow {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getPreference(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(this)
    }

    fun setToken(token: String) {
        getPreference().edit().putString("PREFERENCES_TOKEN", token).apply()
        HttpClient.getInstance().buildRetrofitClient(token)
    }

    fun getToken(): String? {
        return getPreference().getString("PREFERENCES_TOKEN", null)
    }

    fun setUser(user: String) {
        getPreference().edit().putString("PREFERENCES_USER", user).apply()
        HttpClient.getInstance().buildRetrofitClient(user)
    }

    fun getUser(): String? {
        return getPreference().getString("PREFERENCES_USER", null)
    }

}