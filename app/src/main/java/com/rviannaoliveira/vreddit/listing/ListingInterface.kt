package com.rviannaoliveira.vreddit.listing

import com.rviannaoliveira.vreddit.global.ErrorViewInterface
import com.rviannaoliveira.vreddit.global.ProgressViewInterface
import com.rviannaoliveira.vreddit.modal.RedditNewsDataResponse

/**
 * Criado por rodrigo on 18/10/17.
 */
interface ListingInterface {

    interface ListingView : ProgressViewInterface, ErrorViewInterface {
        fun loadNewReddits(reddit: List<RedditNewsDataResponse>?)
    }

    interface ListingPresenter {
        fun onResume()
        fun onPause()
        fun onDestroy()
    }
}