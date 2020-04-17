package com.smartdevservice.lbtechtest.network

import com.smartdevservice.lbtechtest.data.Album
import retrofit2.Call
import retrofit2.http.*

interface RestAPI {

    // @formatter:off

    @GET("technical-test.json")
    fun getAlbums(): Call<List<Album>?>

    // @formatter:on

}