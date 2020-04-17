package com.smartdevservice.lbtechtest.data

import android.os.Parcel
import android.os.Parcelable

class AlbumItem(
    val albumId: Int,
    val id: Int,
    val title: String?,
    val url: String?,
    val thumbnailUrl: String?) : Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return "AlbumItem(albumId=$albumId, id=$id, title=$title, url=$url, thumbnailUrl=$thumbnailUrl)"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(albumId)
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(url)
        parcel.writeString(thumbnailUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AlbumItem> {
        override fun createFromParcel(parcel: Parcel): AlbumItem {
            return AlbumItem(parcel)
        }

        override fun newArray(size: Int): Array<AlbumItem?> {
            return arrayOfNulls(size)
        }
    }
}