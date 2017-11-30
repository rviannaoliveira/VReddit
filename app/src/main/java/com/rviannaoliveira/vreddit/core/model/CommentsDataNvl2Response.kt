package com.rviannaoliveira.vreddit.core.model

import com.rviannaoliveira.vreddit.core.util.DefaultData

/**
 * Criado por rodrigo on 18/10/17.
 */
data class CommentsDataNvl2Response(val data: CommentsDataResponse?,
                                    val kind: String = DefaultData.getString())