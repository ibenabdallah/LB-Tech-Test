package com.smartdevservice.lbtechtest.data

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.smartdevservice.lbtechtest.MyApp
import com.smartdevservice.lbtechtest.network.RestApiFacade
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class SyncRepositoryWorker(context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {
    override fun doWork(): Result {
        Timber.i("Synchronizing books...")
        getAllAlbums()

        return Result.success()
    }

    private fun getAllAlbums() {
        val request = RestApiFacade.restApi.getAlbums()
        request.enqueue(object : Callback<List<Album>?> {
            override fun onResponse(
                call: Call<List<Album>?>,
                response: Response<List<Album>?>
            ) {
                val albums = response.body()
                if (!albums.isNullOrEmpty()) {
                    MyApp.db.albumDao().insertAllAlbums(albums)
                    Timber.d("onResponse : OK, albums.size = ${albums.size}")
                } else {
                    Timber.d("onResponse : KO")
                }
            }

            override fun onFailure(call: Call<List<Album>?>, t: Throwable) {
                Timber.d("onFailure")
            }
        })
    }

}