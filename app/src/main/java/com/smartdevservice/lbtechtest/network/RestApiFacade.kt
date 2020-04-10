package com.smartdevservice.lbtechtest.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * @author B.A.Ismail
 */
object RestApiFacade {

    private const val BASE_API_URL = "https://static.leboncoin.fr/img/shared/"


    private var restAPI: RestAPI? = null

    @JvmStatic
    val restApi: RestAPI
        get() {
            if (restAPI == null) {
                synchronized(RestAPI::class.java) {
                    if (restAPI == null) {

                        val logging = HttpLoggingInterceptor()
                        // set your desired log level
                        logging.level = HttpLoggingInterceptor.Level.BODY
                        val httpClient = OkHttpClient.Builder()
                        // add your other interceptors …
                        // add logging as last interceptor
                        httpClient.addInterceptor(logging) // <-- this is the important line!

                        val retrofit = Retrofit.Builder()
                                .baseUrl(BASE_API_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .client(httpClient.build())
                                .build()
                        restAPI = retrofit.create(RestAPI::class.java)
                    }
                }
            }
            return restAPI as RestAPI
        }
}