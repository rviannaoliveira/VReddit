package com.rviannaoliveira.vreddit.modal

/**
 * Criado por rodrigo on 18/10/17.
 */
data class RedditCommentsChildrenResponse(
        val children: List<RedditCommentsChildrenDataNvl2Response> = emptyList(),
        val before: String,
        val after: String)