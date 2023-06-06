package ru.partyshaker.partyshaker.data.network

import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NetworkApiImpl @Inject constructor() : NetworkApi {

    override fun <T : Any> createApi(baseUrl: String, cls: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .dispatcher(Dispatcher(Executors.newFixedThreadPool(5)))
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .callTimeout(10, TimeUnit.SECONDS)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(cls)

    }
}