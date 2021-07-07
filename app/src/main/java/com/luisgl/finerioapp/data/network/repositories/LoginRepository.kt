package com.luisgl.finerioapp.data.network.repositories

import com.luisgl.finerioapp.data.network.api.LoginAPI
import com.luisgl.finerioapp.data.network.models.request.login.LoginRequest
import com.luisgl.finerioapp.data.network.models.responses.login.LoginResponse
import com.luisgl.finerioapp.data.network.models.responses.me.MeResponse
import retrofit2.Response

class LoginRepository {

    suspend fun postLoginInfo(loginInfo: LoginRequest): Response<LoginResponse>{
        return LoginAPI().postLogin(loginInfo)
    }

    suspend fun getLoginMe (auth: String): Response<MeResponse>{
        return LoginAPI().getLoginMe(auth)
    }
}