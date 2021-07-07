package com.luisgl.finerioapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.luisgl.finerioapp.data.network.repositories.MovementsRepository
import com.luisgl.finerioapp.model.interfaces.home.HomeListener
import com.luisgl.finerioapp.ui.utils.Coroutines

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    // Listener
    var listener: HomeListener ?= null

    fun getMovementsService(token: String, id: String, start: Int, end: Int) {
        listener?.onShowProgress()
        Coroutines.main {
            val response = MovementsRepository().getMovements(token, id, start, end)

            if ( response.isSuccessful ) {
                listener?.onHideProgress()
                listener?.onSuccess(response.body()?.data!!)
            } else {
                listener?.onHideProgress()
                listener?.onError()
            }
        }
    }
}
