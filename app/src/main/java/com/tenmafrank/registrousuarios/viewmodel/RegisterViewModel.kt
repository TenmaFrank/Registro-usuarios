package com.tenmafrank.registrousuarios.viewmodel

import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.tenmafrank.registrousuarios.model.dao.ApiConexion
import com.tenmafrank.registrousuarios.model.dto.LoginSignupRequest
import com.tenmafrank.registrousuarios.utils.Constants
import java.lang.Exception

class RegisterViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

    suspend fun doRegister(userName: String, email: String, pass: String):String{
        var token = Constants.LOGIN_ERROR_LABEL
        var id = Constants.LOGIN_ERROR_LABEL
        try {
            val registerResponse = ApiConexion.apiConexionRetrofitService.register(
                LoginSignupRequest(email,pass)
            )
            id = registerResponse.body()?.id ?: Constants.LOGIN_ERROR_LABEL
            token = registerResponse.body()?.token ?: Constants.LOGIN_ERROR_LABEL
        }
        catch (e: Exception){
            Log.e("register", "Error on api consume" + e.message)
        }
        return id
    }
}