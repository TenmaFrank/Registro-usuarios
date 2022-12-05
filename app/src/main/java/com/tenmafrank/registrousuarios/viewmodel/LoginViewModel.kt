package com.tenmafrank.registrousuarios.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.tenmafrank.registrousuarios.model.dao.ApiConexion
import com.tenmafrank.registrousuarios.model.dto.LoginSignupRequest
import com.tenmafrank.registrousuarios.utils.Constants
import java.lang.Exception

class LoginViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

    suspend fun doLogin(email: String, password: String):String{
        var token = Constants.LOGIN_ERROR_LABEL
            try {
                val loginResponse = ApiConexion.apiConexionRetrofitService.login(
                    LoginSignupRequest(email, password)
                )

                if (loginResponse.code()==200){
                    token = loginResponse.body()?.token ?: "Error"
                }
                else{
                    token = loginResponse.body()?.error ?: "Error"
                }
                Log.i("login", "respuesta: $token")
            }
            catch (e: Exception){
                Log.e("login", "Error on api consume" + e.message)
            }
        return token
    }
}