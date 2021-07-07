package com.luisgl.finerioapp.data.network.models.responses.movements

data class Account(
    val availableBalance: Double,
    val balance: Double,
    val dateCreated: String,
    val deleted: Boolean,
    val id: String,
    val institution: Institution,
    val lastUpdated: String,
    val name: String,
    val number: String,
    val type: String,
    val user: User
)