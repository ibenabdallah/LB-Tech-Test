package com.smartdevservice.lbtechtest.data

import androidx.room.Database
import androidx.room.RoomDatabase


const val DATABASE_NAME = "my_db_albums"

@Database(entities = [Album::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun albumDao() : AlbumDao
}