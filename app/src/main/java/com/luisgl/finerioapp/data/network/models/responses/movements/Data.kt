package com.luisgl.finerioapp.data.network.models.responses.movements

data class Data(
    val account: Account,
    val amount: Double,
    val balance: Double,
    val concepts: List<Concept>,
    val customDate: String,
    val customDescription: String,
    val date: String,
    val dateCreated: String,
    val deleted: Boolean,
    val description: String,
    val duplicated: Boolean,
    val hasConcepts: Boolean,
    val id: String,
    val inResume: Boolean,
    val lastUpdated: String,
    val type: String
)