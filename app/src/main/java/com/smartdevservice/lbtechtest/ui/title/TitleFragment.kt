package com.smartdevservice.lbtechtest.ui.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartdevservice.lbtechtest.R
import com.smartdevservice.lbtechtest.data.Album
import kotlinx.android.synthetic.main.fragment_title.*

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment
 */
class TitleFragment : Fragment() {

    private var titles: ArrayList<Album>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            titles = it.getParcelableArrayList(PARAM_LIST)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_title, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycleView.apply {
            layoutManager =  LinearLayoutManager(this@TitleFragment.context)
            adapter = MyTitleRecyclerViewAdapter(titles)
        }

    }

    companion object {
        const val PARAM_LIST = "PARAM_LIST"
        @JvmStatic
        fun newInstance(titles: ArrayList<Album>) : TitleFragment {
            val fragment = TitleFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList(PARAM_LIST, titles)
            fragment.arguments = bundle
            return fragment
        }
    }
}
