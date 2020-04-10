package com.smartdevservice.lbtechtest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smartdevservice.lbtechtest.R
import com.smartdevservice.lbtechtest.data.AlbumItem
import com.smartdevservice.lbtechtest.network.RestApiFacade.restApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val request = restApi.getAlbums()
        request.enqueue(object : Callback<List<AlbumItem>?> {
            override fun onResponse(call: Call<List<AlbumItem>?>, response: Response<List<AlbumItem>?>) {
                if (response.body() != null) {
                    Timber.d("onResponse : OK")
                } else {
                    Timber.d("onResponse : KO")
                }
            }

            override fun onFailure(call: Call<List<AlbumItem>?>, t: Throwable) {
                Timber.d("onFailure")
            }
        })
    }
}
