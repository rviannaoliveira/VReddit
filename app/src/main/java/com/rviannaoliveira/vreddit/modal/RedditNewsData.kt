package com.rviannaoliveira.vreddit.modal

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
        val score: Int = DefaultData.getInt())