package com.tenmafrank.registrousuarios.model.dao

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tenmafrank.registrousuarios.model.dto.*
import com.tenmafrank.registrousuarios.utils.Constants.BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MetodosDao {
    @Headers("accept: application/json")
    @POST("/api/login")
    suspend fun login(@Body request: LoginSignupRequest): Response<LoginResponse>

    @Headers("accept: application/json")
    @POST("/api/register")
    suspend fun register(@Body request: LoginSignupRequest): Response<RegisterResponse>

    @GET("/api/users")
    suspend fun userList(@Query("page") page: Int): Response<UserListResponse>

    @Headers("accept: application/json")
    @POST("/api/users")
    suspend fun create(@Body updateRequest: CreateUpdateRequest): Response<CreateUpdateResponse>

    @Headers("accept: application/json")
    @PUT("/api/users/2")
    suspend fun update(@Body updateRequest: CreateUpdateRequest): Response<CreateUpdateResponse>
}

object ApiConexion {
    val apiConexionRetrofitService: MetodosDao by lazy {
        retrofit.create(MetodosDao::class.java)
    }
}