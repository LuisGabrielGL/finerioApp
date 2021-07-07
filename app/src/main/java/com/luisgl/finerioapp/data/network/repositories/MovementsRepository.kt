package com.luisgl.finerioapp.data.network.repositories

import com.luisgl.finerioapp.data.network.api.MovementsAPI
import com.luisgl.finerioapp.data.network.models.responses.movements.MovementsResponse
import retrofit2.Response

class MovementsRepository {
    suspend fun getMovements(auth: String, idUserInfo: String, start: Int, end: Int): Response<MovementsResponse>{
        return MovementsAPI().getMovements(
            auth,
            idUserInfo,
            true,
            start,
            end,
            true,
            true,
            true)
    }
}