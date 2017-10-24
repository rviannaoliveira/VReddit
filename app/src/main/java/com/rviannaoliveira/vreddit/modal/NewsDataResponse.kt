package com.rviannaoliveira.vreddit.modal

import com.rviannaoliveira.vreddit.util.DefaultData

/**
 * Criado por rodrigo on 18/10/17.
 */
data class NewsDataResponse(val data: NewsChildrenResponse?,
                            val kind: String = DefaultData.getString())