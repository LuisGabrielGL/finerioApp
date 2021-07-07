package com.luisgl.finerioapp.ui.viewmodel

import android.app.Application
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.luisgl.finerioapp.data.network.models.request.login.LoginRequest
import com.luisgl.finerioapp.data.network.repositories.LoginRepository
import com.luisgl.finerioapp.model.constants.GeneralConstants
import com.luisgl.finerioapp.model.interfaces.login.LoginListener
import com.luisgl.finerioapp.ui.utils.Coroutines

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    //Read Data Input
    val userId = ObservableField("")
    val userPassword = ObservableField("")

    //Listener
    var listener: LoginListener ?= null

    fun onLogin(view: View){

        if (!userId.get().isNullOrEmpty() && !userPassword.get().isNullOrEmpty()) {
            listener?.onShowProgress()

            val userInfo = LoginRequest(
                userId.get()!!,
                userPassword.get()!!
            )
            getLoginService(userInfo)

        } else {
            listener?.onHideProgress()
            listener?.onErrorLogin(0)
        }

    }


    private fun getLoginService( userInfo: LoginRequest ) {

        Coroutines.main {
            val response = LoginRepository().postLoginInfo(userInfo)

            if ( response.isSuccessful ) {
                getMeService(GeneralConstants.TOKEN_BEARER,response.body()?.accessToken!!)
            } else {
                listener?.onHideProgress()

                if ( response.code() == 401 ) {
                    listener?.onErrorLogin(1)
                } else {
                    listener?.onErrorLogin(2)
                }
            }
        }
    }

    private fun getMeService (type: String, token: String) {

        Coroutines.main {
            val response = LoginRepository().getLoginMe(type + token)

            if ( response.isSuccessful ) {
                listener?.onSuccess( type + token, response.body()?.id!!, userId.get()!! )
            } else {
                listener?.onHideProgress()

                if ( response.code() == 401 ) {
                    listener?.onErrorLogin(1)
                } else {
                    listener?.onErrorLogin(2)
                }
            }
        }
    }

}