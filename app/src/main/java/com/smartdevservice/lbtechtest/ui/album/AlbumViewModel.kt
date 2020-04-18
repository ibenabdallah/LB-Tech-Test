package com.smartdevservice.lbtechtest.ui.album

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.smartdevservice.lbtechtest.MyApp
import com.smartdevservice.lbtechtest.data.Album

class AlbumViewModel : ViewModel(){

    var albums : LiveData<HashMap<Int, ArrayList<Album>>>

    init {
        albums = Transformations.switchMap(MyApp.db.albumDao().getAllAlbums()) { albumList ->
            findAlbums(albumList)
        }
    }

    private fun findAlbums(albumList: List<Album>) : LiveData<HashMap<Int, ArrayList<Album>>> {
        val map: HashMap<Int, ArrayList<Album>> = HashMap()
        val albums = MutableLiveData<HashMap<Int, ArrayList<Album>>>()
        for (item in albumList) {
            if (map[item.albumId].isNullOrEmpty()) {
                map[item.albumId] = arrayListOf(item)
            } else {
                map[item.albumId]?.add(item)
            }
        }
        albums.value = map
        return albums
    }

    fun getAllAlbums(context: Context) {
        MyApp.repository.syncAlbums(context)
    }

}

