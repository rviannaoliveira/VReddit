package com.rviannaoliveira.vreddit.core.model

import com.rviannaoliveira.vreddit.core.util.DefaultData

/**
 * Criado por rodrigo on 18/10/17.
 */
data class NewsDataResponse(val data: NewsChildrenResponse?,
                            val kind: String = DefaultData.getString())