package com.example.roomloginapp.data.repository

import com.example.roomloginapp.data.retrofit.ApiHelper
import com.example.roomloginapp.data.retrofit.RetrofitBuilder
import javax.inject.Inject

/**
 * Repository for [Coin] class
 */
class CoinRepository @Inject constructor() {

    private val apiHelper: ApiHelper = ApiHelper(RetrofitBuilder.apiService)

    suspend fun getCoins() = apiHelper.getCoins()
}