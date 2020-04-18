package com.smartdevservice.lbtechtest.ui.title


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smartdevservice.lbtechtest.R
import com.smartdevservice.lbtechtest.data.Album
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_title_item.view.*

/**
 * [RecyclerView.Adapter] that can display a [Album]
 *
 */
class MyTitleRecyclerViewAdapter(
    private val mValues: List<Album>?
) : RecyclerView.Adapter<MyTitleRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_title_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues?.get(position)
        holder.titleTextView.text = item?.title

        Picasso.get()
            .load(item?.thumbnailUrl)
            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
            .into(holder.iconImageView)
    }

    override fun getItemCount(): Int = mValues?.size?: 0

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iconImageView: ImageView = view.album_icon
        val titleTextView: TextView = view.album_title

        override fun toString(): String {
            return "Title = ${titleTextView.text}"
        }
    }
}
