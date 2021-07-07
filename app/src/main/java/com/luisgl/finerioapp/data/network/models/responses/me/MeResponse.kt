package com.luisgl.finerioapp.data.network.models.responses.me

data class MeResponse(
    val accountExpired: Boolean,
    val accountLocked: Boolean,
    val customerId: Int,
    val dateCreated: String,
    val email: String,
    val enabled: Boolean,
    val finerioCode: String,
    val hasNewTerms: Boolean,
    val id: String,
    val lastUpdated: String,
    val name: String,
    val notificationsEnabled: Boolean,
    val passwordExpired: Boolean,
    val signupCredentialEmailSent: Boolean,
    val signupCredentialPushSent: Boolean,
    val signupEmailSent: Boolean,
    val signupFrom: String,
    val termsAndConditionsAccepted: Boolean,
    val type: String
)