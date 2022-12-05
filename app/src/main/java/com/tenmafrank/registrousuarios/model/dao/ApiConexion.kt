package com.tenmafrank.registrousuarios.model.dao

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tenmafrank.registrousuarios.model.dto.LoginResponse
import com.tenmafrank.registrousuarios.model.dto.LoginSignupRequest
import com.tenmafrank.registrousuarios.model.dto.RegisterResponse
import com.tenmafrank.registrousuarios.model.dto.UserListResponse
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

    @Headers("accept: application/json")
    @GET("/api/users")
    suspend fun userList(@Query("page") page: Int): Response<UserListResponse>
}

object ApiConexion {
    val apiConexionRetrofitService: MetodosDao by lazy {
        retrofit.create(MetodosDao::class.java)
    }
}