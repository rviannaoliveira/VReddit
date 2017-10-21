package com.rviannaoliveira.vreddit.modal

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.rviannaoliveira.vreddit.util.DefaultData

/**
 * Criado por rodrigo on 18/10/17.
 */
data class RedditNewsData(
        val title: String = DefaultData.getString(),
        val id: String = DefaultData.getString(),
        val thumbnail: String = DefaultData.getString(),
        val author: String = DefaultData.getString(),
        val created: Long = DefaultData.getLong(),
        val url: String = DefaultData.getString(),
        val selftext: String = DefaultData.getString(),
        @SerializedName("num_comments") val numComments: Int = DefaultData.getInt(),
        val score: Int = DefaultData.getInt()) : Parcelable {


    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(id)
        parcel.writeString(thumbnail)
        parcel.writeString(author)
        parcel.writeLong(created)
        parcel.writeString(url)
        parcel.writeString(selftext)
        parcel.writeInt(numComments)
        parcel.writeInt(score)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RedditNewsData> {
        override fun createFromParcel(parcel: Parcel): RedditNewsData {
            return RedditNewsData(parcel)
        }

        override fun newArray(size: Int): Array<RedditNewsData?> {
            return arrayOfNulls(size)
        }
    }
}