package com.smartdevservice.lbtechtest

import android.app.Application
import androidx.room.Room
import com.smartdevservice.lbtechtest.data.AlbumRepository
import com.smartdevservice.lbtechtest.data.AppDatabase
import com.smartdevservice.lbtechtest.data.DATABASE_NAME
import timber.log.Timber

class MyApp : Application() {

    companion object {
        lateinit var db: AppDatabase
        lateinit var repository: AlbumRepository
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        db = Room.databaseBuilder(
            this,
            AppDatabase::class.java, DATABASE_NAME
        )
            .allowMainThreadQueries()
            .build()

        repository = AlbumRepository()
    }
}