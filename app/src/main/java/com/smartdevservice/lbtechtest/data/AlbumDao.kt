package com.smartdevservice.lbtechtest.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface AlbumDao {

    @Query("SELECT * FROM album ")
    fun getAllAlbums() : LiveData<List<Album>>

    @Query("SELECT * FROM album WHERE id = :id")
    fun getAlbumById(id: Int) : LiveData<Album>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAlbums(albums: List<Album>)

    @Delete
    fun deleteAlbum(album: Album)
}