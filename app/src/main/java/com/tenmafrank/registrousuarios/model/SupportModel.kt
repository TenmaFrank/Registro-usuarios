package com.tenmafrank.registrousuarios.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SupportModel(
    @Json(name = "url") val url: String,
    @Json(name = "text") val text: String
)
