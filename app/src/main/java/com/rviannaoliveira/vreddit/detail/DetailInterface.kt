package com.rviannaoliveira.vreddit.detail

import com.rviannaoliveira.vreddit.core.global.ErrorViewInterface
import com.rviannaoliveira.vreddit.core.global.ProgressViewInterface
import com.rviannaoliveira.vreddit.core.model.CommentData

/**
 * Criado por rodrigo on 20/10/17.
 */
interface DetailInterface {

    interface DetailView : ProgressViewInterface, ErrorViewInterface {
        fun loadComments(comments: List<CommentData>)
    }

    interface DetailPresenter {
        fun onViewCreated(id: String, connectedToInternet: Boolean)
        fun onDestroy()
    }
}