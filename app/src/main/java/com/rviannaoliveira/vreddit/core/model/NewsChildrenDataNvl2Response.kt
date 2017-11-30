package com.rviannaoliveira.vreddit.core.model

import com.rviannaoliveira.vreddit.core.util.DefaultData

/**
 * Criado por rodrigo on 18/10/17.
 */
data class NewsChildrenDataNvl2Response(val data: NewsData?,
                                        val kind: String = DefaultData.getString())