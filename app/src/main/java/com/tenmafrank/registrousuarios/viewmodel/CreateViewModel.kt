package com.tenmafrank.registrousuarios.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.tenmafrank.registrousuarios.model.dao.ApiConexion
import com.tenmafrank.registrousuarios.model.dto.CreateUpdateRequest
import com.tenmafrank.registrousuarios.model.dto.CreateUpdateResponse
import java.lang.Exception

class CreateViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    suspend fun doCreate(name: String, job: String): CreateUpdateResponse? {
        var res = CreateUpdateResponse("","","","","")
        try {
            val aux = ApiConexion.apiConexionRetrofitService.create(
                CreateUpdateRequest(name,job)
            )
            if (aux.isSuccessful){
                res = aux.body()!!
            }
            else{
                Log.e("login", "resultado de api no satifactorio")
            }
        }
        catch (e: Exception){
            Log.e("login", "Error on api consume" + e.message)
        }
        return res
    }

    suspend fun doUpdate(name: String, job: String): CreateUpdateResponse? {
        var res = CreateUpdateResponse("","","","","")
        try {
            val aux = ApiConexion.apiConexionRetrofitService.update(
                CreateUpdateRequest(name,job)
            )
            if (aux.isSuccessful){
                res = aux.body()!!
            }
            else{
                Log.e("login", "resultado de api no satifactorio")
            }
        }
        catch (e: Exception){
            Log.e("login", "Error on api consume" + e.message)
        }
        return res
    }

}