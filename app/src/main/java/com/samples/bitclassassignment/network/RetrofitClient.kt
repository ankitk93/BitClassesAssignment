package com.samples.bitclassassignment.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by ak93.droid@gmail.com on 23,August,2020
 */
const val BASE_URL = "https://5f3fdac644212d0016fed4f7.mockapi.io/api"

object RetrofitClient{
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}