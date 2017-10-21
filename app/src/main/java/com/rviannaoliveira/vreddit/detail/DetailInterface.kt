package com.rviannaoliveira.vreddit.detail

import com.rviannaoliveira.vreddit.global.ErrorViewInterface
import com.rviannaoliveira.vreddit.global.ProgressViewInterface
import com.rviannaoliveira.vreddit.modal.RedditCommentData

/**
 * Criado por rodrigo on 20/10/17.
 */
interface DetailInterface {

    interface DetailView : ProgressViewInterface, ErrorViewInterface {
        fun loadComments(comments: List<RedditCommentData>)
    }

    interface DetailPresenter {
        fun onViewCreated()
        fun onDestroy()
    }
}