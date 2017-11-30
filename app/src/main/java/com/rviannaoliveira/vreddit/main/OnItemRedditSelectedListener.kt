package com.rviannaoliveira.vreddit.main

import com.rviannaoliveira.vreddit.core.model.NewsData

/**
 * Criado por rodrigo on 22/10/17.
 */
interface OnItemRedditSelectedListener {
    fun onItemSelected(new: NewsData)
}