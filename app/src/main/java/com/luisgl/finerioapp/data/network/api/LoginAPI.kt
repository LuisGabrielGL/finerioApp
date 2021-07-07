package com.luisgl.finerioapp.data.network.api

import com.luisgl.finerioapp.data.network.models.request.login.LoginRequest
import com.luisgl.finerioapp.data.network.models.responses.login.LoginResponse
import com.luisgl.finerioapp.data.network.models.responses.me.MeResponse
import com.luisgl.finerioapp.model.constants.Enviroment
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginAPI {
    @POST(Enviroment.LOGIN)
    suspend fun postLogin(@Body loginUserInfo: LoginRequest): Response<LoginResponse>

    @GET(Enviroment.LOGIN_ME)
    suspend fun getLoginMe(
        @Header("Authorization") token: String
    ): Response<MeResponse>

    companion object {
        operator fun invoke():LoginAPI{
            return  Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Enviroment.URL_BASE)
                .build()
                .create(LoginAPI::class.java)
        }
    }

}