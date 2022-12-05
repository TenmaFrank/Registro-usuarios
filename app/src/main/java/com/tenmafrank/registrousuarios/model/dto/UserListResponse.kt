package com.tenmafrank.registrousuarios.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tenmafrank.registrousuarios.model.DataModel
import com.tenmafrank.registrousuarios.model.SupportModel

@JsonClass(generateAdapter = true)
data class UserListResponse(
    @Json(name = "page") var page: Int = 0,
    @Json(name = "per_page") var perPage: Int = 0,
    @Json(name = "total") var total: Int = 0,
    @Json(name = "total_pages") var totalPages: Int = 0,
    @Json(name = "data") var data: List<DataModel>,
    @Json(name = "support") var support: SupportModel
)
