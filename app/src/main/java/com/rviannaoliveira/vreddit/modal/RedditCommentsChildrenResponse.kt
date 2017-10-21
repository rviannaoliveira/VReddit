package com.rviannaoliveira.vreddit.modal

import com.rviannaoliveira.vreddit.util.DefaultData

/**
 * Criado por rodrigo on 18/10/17.
 */
data class RedditCommentsChildrenResponse(val children: List<RedditCommentsChildrenDataNvl2Response> = emptyList(),
                                          val before: String = DefaultData.getString(),
                                          val after: String = DefaultData.getString())