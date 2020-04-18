package com.smartdevservice.lbtechtest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smartdevservice.lbtechtest.R
import com.smartdevservice.lbtechtest.data.Album
import com.smartdevservice.lbtechtest.ui.album.AlbumFragment
import com.smartdevservice.lbtechtest.ui.title.TitleFragment
import timber.log.Timber

class MainActivity : AppCompatActivity(), OnListListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.d("MainActivity : onCreate")

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.container, AlbumFragment.newInstance()).commit()
    }

    override fun onItemClick(items: ArrayList<Album>) {
        Timber.d("onItemClick : items.size = ${items.size}")
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.container, TitleFragment.newInstance(items))
            .addToBackStack("TitleFragment").commit()
    }
}

/**
 * This interface must be implemented by activities that contain this
 * fragment to allow an interaction in this fragment to be communicated
 * to the activity and potentially other fragments contained in that
 * activity.
 *
 *
 * See the Android Training lesson
 * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
 * for more information.
 */
interface OnListListener {
    fun onItemClick(items: ArrayList<Album>)
}