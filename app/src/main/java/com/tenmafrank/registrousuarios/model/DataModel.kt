package com.tenmafrank.registrousuarios.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataModel(
    @Json(name = "id") var id: Int,
    @Json(name = "email") var email: String,
    @Json(name = "first_name") var firstName: String,
    @Json(name = "last_name") var lastName: String = "",
    @Json(name = "avatar") var avatar: String,
)
