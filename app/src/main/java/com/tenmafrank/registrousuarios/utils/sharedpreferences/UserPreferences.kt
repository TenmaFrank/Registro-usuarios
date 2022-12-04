package com.tenmafrank.registrousuarios.utils.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

const val SHARED_FILE = "UserSec"
const val SHARED_NAME_USER = "name"
const val SHARED_EMAIL_USER = "email"
const val SHARED_TEMPORAL_PASSWORD = "qwertyuio"

class UserPreferences (val context: Context){
    private val enSharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        SHARED_FILE,
        MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveNameUser(name: String){
        enSharedPreferences.edit().putString(SHARED_NAME_USER, name).apply()
    }
    fun saveEmailUser(eMailUser: String){
        enSharedPreferences.edit().putString(SHARED_EMAIL_USER, eMailUser).apply()
    }
    fun saveTemporalPassword(temporalPassword: String){
        enSharedPreferences.edit().putString(SHARED_TEMPORAL_PASSWORD,temporalPassword).apply()
    }
    fun getNameUser(): String{
        return enSharedPreferences.getString(SHARED_NAME_USER, "")!!
    }
    fun getEmailUser():String{
        return enSharedPreferences.getString(SHARED_EMAIL_USER,"")!!
    }
    fun getTemporalPassword(): String{
        return enSharedPreferences.getString(SHARED_TEMPORAL_PASSWORD,"")!!
    }

}