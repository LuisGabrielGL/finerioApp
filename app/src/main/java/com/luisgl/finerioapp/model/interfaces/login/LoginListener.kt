package com.luisgl.finerioapp.model.interfaces.login

interface LoginListener {
    fun onSuccess(token: String, id: String, email: String)
    fun onErrorLogin(errorType: Int)
    fun onShowProgress()
    fun onHideProgress()
}