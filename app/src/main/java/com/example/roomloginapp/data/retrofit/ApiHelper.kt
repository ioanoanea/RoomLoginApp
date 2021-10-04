package com.example.roomloginapp.data.retrofit

import javax.inject.Inject

/**
 * Api Helper class
 */
class ApiHelper(private val apiService: ApiService) {

    suspend fun getCoins() = apiService.getCoins()
}