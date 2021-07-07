package com.luisgl.finerioapp.data.network.models.responses.movements

data class Concept(
    val amount: Double,
    val category: Category,
    val description: String,
    val id: String,
    val movement: Any,
    val type: String
)