package com.franyanes.listapp.data.service

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class GlobalLogicModule {

    // quise usar https pero me esta tirando errores con el certificate
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideGlobalLogicService(): GlobalLogicService {
        return retrofit.create(GlobalLogicService::class.java)
    }

    private companion object {
        const val BASE_URL = "http://private-f0eea-mobilegllatam.apiary-mock.com/"
    }
}
