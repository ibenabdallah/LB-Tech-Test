package com.smartdevservice.lbtechtest.ui.title


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smartdevservice.lbtechtest.R
import com.smartdevservice.lbtechtest.data.AlbumItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_title_item.view.*

/**
 * [RecyclerView.Adapter] that can display a [AlbumItem]
 *
 */
class MyTitleRecyclerViewAdapter(
    private val context: Context,
    private val mValues: List<AlbumItem>?
) : RecyclerView.Adapter<MyTitleRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_title_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues?.get(position)
        holder.titleTextView.text = item?.title
        val builder = Picasso.Builder(context)
        builder.build().load(item?.thumbnailUrl).into(holder.iconImageView)
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
