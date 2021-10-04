package com.example.roomloginapp.data.retrofit

import com.example.roomloginapp.data.model.Coin
import retrofit2.http.GET

/**
 * Api service interface
 */
interface ApiService {
    @GET("coins/list")
    suspend fun getCoins(): List<Coin>
}