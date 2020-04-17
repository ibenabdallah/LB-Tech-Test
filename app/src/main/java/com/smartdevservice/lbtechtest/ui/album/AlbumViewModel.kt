package com.smartdevservice.lbtechtest.ui.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smartdevservice.lbtechtest.data.AlbumItem
import com.smartdevservice.lbtechtest.network.RestApiFacade.restApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class AlbumViewModel : ViewModel(){

    val albums = MutableLiveData<HashMap<Int, ArrayList<AlbumItem>>>()

    fun getAllAlbums() {
        val request = restApi.getAlbums()
        request.enqueue(object : Callback<List<AlbumItem>?> {
            override fun onResponse(
                call: Call<List<AlbumItem>?>,
                response: Response<List<AlbumItem>?>
            ) {
                val map: HashMap<Int, ArrayList<AlbumItem>> = HashMap()
                val responses = response.body()
                if (!responses.isNullOrEmpty()) {
                    for (item in responses){
                        if(map[item.albumId].isNullOrEmpty()) {
                            map[item.albumId] = arrayListOf(item)
                        }else{
                            map[item.albumId]?.add(item)
                        }
                    }
                    albums.postValue(map)
                    Timber.d("onResponse : OK, map.size = ${map.size}")
                } else {
                    Timber.d("onResponse : KO")
                }
            }

            override fun onFailure(call: Call<List<AlbumItem>?>, t: Throwable) {
                Timber.d("onFailure")
            }
        })
    }

    fun getTitleByIdAlbum(albumId: Int) : LiveData<ArrayList<AlbumItem>> {
        Timber.d("onResponse : OK, titles.size = ${albums.value?.get(albumId)?.size}")
        val liveData = MutableLiveData<ArrayList<AlbumItem>>()
        liveData.value = albums.value?.get(albumId)
        return liveData
    }

}

