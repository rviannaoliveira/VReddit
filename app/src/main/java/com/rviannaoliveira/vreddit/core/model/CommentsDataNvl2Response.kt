package com.rviannaoliveira.vreddit.modal

import com.rviannaoliveira.vreddit.util.DefaultData

/**
 * Criado por rodrigo on 18/10/17.
 */
data class CommentsDataNvl2Response(val data: CommentsDataResponse?,
                                    val kind: String = DefaultData.getString())