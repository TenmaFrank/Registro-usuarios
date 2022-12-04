package com.tenmafrank.registrousuarios.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginSignupRequest(
    @Json(name = "email") var email: String,
    @Json(name = "password") var password: String
)
