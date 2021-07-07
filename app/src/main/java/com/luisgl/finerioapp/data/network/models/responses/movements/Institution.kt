package com.luisgl.finerioapp.data.network.models.responses.movements

data class Institution(
    val code: String,
    val id: Int,
    val institutionType: String,
    val name: String,
    val status: String
)