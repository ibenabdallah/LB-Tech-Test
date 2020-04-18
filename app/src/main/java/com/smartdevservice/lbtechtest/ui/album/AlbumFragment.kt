package com.smartdevservice.lbtechtest.ui.album

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.smartdevservice.lbtechtest.R
import com.smartdevservice.lbtechtest.ui.OnListListener

import kotlinx.android.synthetic.main.fragment_album.*

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [OnListListener] interface.
 */
class AlbumFragment : Fragment() {

    private lateinit var albumViewModel: AlbumViewModel
    private var listener: OnListListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the adapter
            with(recycleView) {
                layoutManager = GridLayoutManager(context, 2)
            }

        albumViewModel = ViewModelProvider(this).get(AlbumViewModel::class.java)
        activity?.let {activity ->
            albumViewModel.albums.observe(activity, Observer { albums ->
                recycleView.adapter = MyAlbumRecyclerViewAdapter(activity, albums, listener)
            })
        }


        albumViewModel.getAllAlbums(context!!)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    companion object {

        @JvmStatic
        fun newInstance() = AlbumFragment()
    }
}
