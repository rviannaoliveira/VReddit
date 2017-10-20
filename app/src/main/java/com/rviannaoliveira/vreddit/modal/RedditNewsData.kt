package com.rviannaoliveira.vreddit.modal

import com.google.gson.annotations.SerializedName

/**
 * Criado por rodrigo on 18/10/17.
 */
data class RedditNewsData(
        val title: String,
        val id: String,
        val thumbnail: String,
        val author: String,
        val created: Long,
        val url: String,
        val selftext: String,
        @SerializedName("num_comments") val numComments: Int,
        val score: Int)