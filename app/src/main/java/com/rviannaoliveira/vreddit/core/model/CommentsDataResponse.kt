package com.rviannaoliveira.vreddit.core.model

import com.rviannaoliveira.vreddit.core.util.DefaultData

/**
 * Criado por rodrigo on 18/10/17.
 */
data class CommentsDataResponse(val children: List<CommentsChildrenDataNvl2Response> = emptyList(),
                                val before: String = DefaultData.getString(),
                                val after: String = DefaultData.getString())