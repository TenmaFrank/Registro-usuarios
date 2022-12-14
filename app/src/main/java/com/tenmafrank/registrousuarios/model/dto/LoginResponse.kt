package com.tenmafrank.registrousuarios.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    @Json(name = "token") var token: String = "",
    @Json(name = "error") var error: String = ""
)
