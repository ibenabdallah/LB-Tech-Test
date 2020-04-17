package com.smartdevservice.lbtechtest.ui.album


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smartdevservice.lbtechtest.R
import com.smartdevservice.lbtechtest.data.Album
import com.smartdevservice.lbtechtest.ui.OnListListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_album_item.view.*

/**
 * [RecyclerView.Adapter] that can display a [Album] and makes a call to the
 * specified [OnListListener].
 */
class MyAlbumRecyclerViewAdapter(
    private val context: Context,
    private val mValues: HashMap<Int, ArrayList<Album>>,
    private val mListener: OnListListener?
) : RecyclerView.Adapter<MyAlbumRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as ArrayList<Album>
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onItemClick(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_album_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.titleTextView.text = item?.get(0)?.title
        holder.numberTitleTextView.text = "Cette album contient ${item?.size} titre"
        val builder = Picasso.Builder(context)
        builder.build().load(item?.get(0)?.url).into(holder.iconImageView)
        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size
    private fun getItem(position: Int): ArrayList<Album>? {
        return mValues[mValues.keys.elementAt(position)]
    }
    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val iconImageView: ImageView = mView.album_icon
        val titleTextView: TextView = mView.album_title
        val numberTitleTextView: TextView = mView.album_number_title

        override fun toString(): String {
            return super.toString() + " '" + titleTextView.text + "'"
        }
    }
}
