package com.tenmafrank.registrousuarios.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterResponse(
    @Json(name = "id") var id:String,
    @Json(name = "token") val token:String
    )
