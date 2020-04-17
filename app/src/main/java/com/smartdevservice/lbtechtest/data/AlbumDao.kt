package com.smartdevservice.lbtechtest.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface AlbumDao {

    @Query("SELECT * FROM album")
    fun getAllAlbums() : LiveData<List<Album>>

    @Query("SELECT * FROM album WHERE id = :id")
    fun getAlbumById(id: Int) : LiveData<Album>

    @Insert
    fun insertAlbum(album: Album)

    @Delete
    fun deleteAlbum(album: Album)
}