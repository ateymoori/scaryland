package com.pixabay.utils.di

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Inject

/**
 *
 * This class makes the Retrofit client
 */

class RetrofitServiceGenerator @Inject constructor(
    private val textConverter: ScalarsConverterFactory,
    private val converter: GsonConverterFactory,
    private val httpClient: OkHttpClient.Builder,
    private val baseURL: String
) {

    /**
     * Creates a Retrofit Client object
     */
    fun getClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(textConverter)
            .addConverterFactory(converter)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build()
    }
}