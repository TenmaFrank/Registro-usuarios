package com.tenmafrank.registrousuarios.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.tenmafrank.registrousuarios.model.DataModel
import com.tenmafrank.registrousuarios.model.dao.ApiConexion
import java.lang.Exception

class ListViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

    suspend fun doUserList(): List<DataModel>? {
        var lista : List<DataModel>? = null
        try {
            val listaAux = mutableListOf<DataModel>()
            for (i in 0..1){
                val aux = i + 1
                val loginResponse = ApiConexion.apiConexionRetrofitService.userList(i + 1)
                for (item in loginResponse.body()?.data!!){
                    listaAux.add(item)
                }
            }
            lista = listaAux
        }
        catch (e: Exception){
            Log.e("login", "Error on api consume" + e.message)
        }
        return lista
    }
}