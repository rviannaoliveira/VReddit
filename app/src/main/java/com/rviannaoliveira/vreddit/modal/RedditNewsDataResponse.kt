package com.rviannaoliveira.vreddit.modal

import com.google.gson.annotations.SerializedName

/**
 * Criado por rodrigo on 18/10/17.
 */
data class RedditNewsDataResponse(
        val title: String,
        val id: String,
        val thumbnail: String,
        val author: String,
        @SerializedName("num_comments") val numComments: Int,
        val score: Int)