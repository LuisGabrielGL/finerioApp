package com.luisgl.finerioapp.data.network.api


import com.luisgl.finerioapp.data.network.models.responses.movements.MovementsResponse
import com.luisgl.finerioapp.model.constants.Enviroment
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovementsAPI {
    @GET(Enviroment.MOVEMENTS+"{id}"+Enviroment.MOVEMENTS_DATA)
    suspend fun getMovements(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Query("deep") deep: Boolean,
        @Query("offset") offset: Int,
        @Query("max") max: Int,
        @Query("includeCharges") includeCharges: Boolean,
        @Query("includeDeposits") includeDeposits: Boolean,
        @Query("includeDuplicates") includeDuplicates: Boolean
    ): Response<MovementsResponse>

    companion object {
        operator fun invoke():MovementsAPI{
            return  Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Enviroment.URL_BASE)
                .build()
                .create(MovementsAPI::class.java)
        }
    }

}