package com.luisgl.finerioapp.data.network.models.responses.movements

data class Category(
    val color: String,
    val id: String,
    val name: String,
    val parent: Parent,
    val textColor: String
)