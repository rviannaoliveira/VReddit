package com.rviannaoliveira.vreddit.core.model

import com.rviannaoliveira.vreddit.core.util.DefaultData

/**
 * Criado por rodrigo on 18/10/17.
 */
data class CommentsChildrenDataNvl2Response(val data: CommentData?,
                                            val kind: String = DefaultData.getString())