package com.tenmafrank.registrousuarios.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateUpdateResponse(
    @Json(name = "name") var name: String,
    @Json(name = "job") var job: String,
    @Json(name = "id") var id: String = "",
    @Json(name = "createdAt") var createdAt: String = "",
    @Json(name = "updatedAt") var updatedAt: String = ""
)
