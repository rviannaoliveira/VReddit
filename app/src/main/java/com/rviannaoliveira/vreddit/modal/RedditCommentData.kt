package com.rviannaoliveira.vreddit.modal

import com.rviannaoliveira.vreddit.util.DefaultData

/**
 * Criado por rodrigo on 18/10/17.
 */
data class RedditCommentData(
        val author: String = DefaultData.getString(),
        val created: Long = DefaultData.getLong(),
        val body: String = DefaultData.getString())