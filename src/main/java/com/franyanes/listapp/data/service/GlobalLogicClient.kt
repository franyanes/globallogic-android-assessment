package com.franyanes.listapp.data.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GlobalLogicClient {

    // quise usar https porque me esta tirando errores con el certificate
    private const val BASE_URL = "http://private-f0eea-mobilegllatam.apiary-mock.com/"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val globalLogicService: GlobalLogicService by lazy {
        retrofit.create(GlobalLogicService::class.java)
    }
}
