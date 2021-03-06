package com.luisgl.finerioapp.data.network.models.responses.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("expires_in")
    val expiresIn: Int,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("roles")
    val roles: List<String>,
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("username")
    val username: String
)