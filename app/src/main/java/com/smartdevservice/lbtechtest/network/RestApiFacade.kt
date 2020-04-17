package com.smartdevservice.lbtechtest.network

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
                        val retrofit = Retrofit.Builder()
                            .baseUrl(BASE_API_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                        restAPI = retrofit.create(RestAPI::class.java)
                    }
                }
            }
            return restAPI as RestAPI
        }
}