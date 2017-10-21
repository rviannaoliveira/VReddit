package com.rviannaoliveira.vreddit.modal

/**
 * Criado por rodrigo on 18/10/17.
 */
data class RedditNewsChildrenResponse(
        val children: List<RedditNewsChildrenDataNvl2Response> = emptyList(),
        val before: String,
        val after: String)