package com.rviannaoliveira.vreddit.listing

import com.rviannaoliveira.vreddit.global.ErrorViewInterface
import com.rviannaoliveira.vreddit.global.ProgressViewInterface
import com.rviannaoliveira.vreddit.modal.RedditNewsData

/**
 * Criado por rodrigo on 18/10/17.
 */
interface ListingInterface {

    interface ListingView : ProgressViewInterface, ErrorViewInterface {
        fun loadNewReddits(news: List<RedditNewsData>)
        fun saveNextPage(after: String)
    }

    interface ListingPresenter {
        fun onViewCreated(connectedToInternet: Boolean)
        fun onDestroy()
        fun loadNextPageNewRedditsList(after: String?)
    }
}