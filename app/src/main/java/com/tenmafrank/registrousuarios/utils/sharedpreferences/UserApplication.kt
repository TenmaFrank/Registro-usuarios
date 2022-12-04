package com.tenmafrank.registrousuarios.utils.sharedpreferences

import android.app.Application

class UserApplication : Application() {
    companion object{
        lateinit var preferences: UserPreferences
    }

    override fun onCreate() {
        super.onCreate()
        preferences = UserPreferences(applicationContext)
    }
}