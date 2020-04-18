package com.smartdevservice.lbtechtest.data

import android.content.Context
import androidx.work.*
import timber.log.Timber

class AlbumRepository {

    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    fun syncAlbums(context: Context) {
        Timber.i("Synchronizing albums ...")
        val work = OneTimeWorkRequest.Builder(SyncRepositoryWorker::class.java)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context)
            .beginUniqueWork("syncAlbums", ExistingWorkPolicy.KEEP, work)
            .enqueue()
    }
}