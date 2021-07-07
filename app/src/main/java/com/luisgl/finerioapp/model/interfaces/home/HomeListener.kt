package com.luisgl.finerioapp.model.interfaces.home

import com.luisgl.finerioapp.data.network.models.responses.movements.Data

interface HomeListener {
    fun onSuccess(data: List<Data>)
    fun onError()
    fun onShowProgress()
    fun onHideProgress()
}