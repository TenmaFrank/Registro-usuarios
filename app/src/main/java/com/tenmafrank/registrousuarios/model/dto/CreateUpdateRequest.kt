package com.tenmafrank.registrousuarios.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateUpdateRequest(
    @Json(name = "name") var name: String,
    @Json(name = "job") var job: String
)
