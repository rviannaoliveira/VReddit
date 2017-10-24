package com.rviannaoliveira.vreddit.modal

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.rviannaoliveira.vreddit.util.DefaultData

/**
 * Criado por rodrigo on 18/10/17.
 */
@Entity(tableName = "news")
data class NewsData(
        @PrimaryKey
        var id: String = DefaultData.getString(),
        var title: String = DefaultData.getString(),
        var thumbnail: String = DefaultData.getString(),
        var author: String = DefaultData.getString(),
        var created: Long = DefaultData.getLong(),
        var url: String = DefaultData.getString(),
        var selftext: String = DefaultData.getString(),
        @SerializedName("num_comments") var numComments: Int = DefaultData.getInt(),
        var score: Int = DefaultData.getInt()) : Parcelable {

    @Ignore
    constructor() : this(
            DefaultData.getString(),
            DefaultData.getString(),
            DefaultData.getString(),
            DefaultData.getString(),
            DefaultData.getLong(),
            DefaultData.getString(),
            DefaultData.getString(),
            DefaultData.getInt(),
            DefaultData.getInt())

    @Ignore
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
        parcel.writeString(id)
        parcel.writeString(title)
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

    companion object CREATOR : Parcelable.Creator<NewsData> {
        override fun createFromParcel(parcel: Parcel): NewsData {
            return NewsData(parcel)
        }

        override fun newArray(size: Int): Array<NewsData?> {
            return arrayOfNulls(size)
        }
    }
}