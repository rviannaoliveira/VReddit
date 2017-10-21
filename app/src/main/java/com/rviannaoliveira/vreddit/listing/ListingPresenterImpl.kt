package com.rviannaoliveira.vreddit.listing

import com.rviannaoliveira.vreddit.data.DataManagerFactory
import com.rviannaoliveira.vreddit.data.DataManagerInterface
import com.rviannaoliveira.vreddit.modal.RedditDataResponse
import com.rviannaoliveira.vreddit.modal.RedditNewsData
import io.reactivex.Maybe
import timber.log.Timber

/**
 * Criado por rodrigo on 18/10/17.
 */
class ListingPresenterImpl(private var listingView: ListingInterface.ListingView?,
                           private var dataManagerInterface: DataManagerInterface? = DataManagerFactory.getDefaultInstance()) : ListingInterface.ListingPresenter {
    override fun onViewCreated() {
        listingView?.showProgressBar()
        loadNewRedditsList()
    }

    override fun onDestroy() {
        listingView = null
        dataManagerInterface = null
    }

    private fun loadNewRedditsList() {
        val observableReddits = dataManagerInterface?.getNewReddit()

        observableReddits?.let {
            observableReddits.concatMap({ dataWrappers ->
                getMaybeReddits(dataWrappers)
            }).subscribe({ reddit ->
                listingView?.loadNewReddits(reddit)
                listingView?.hideProgressBar()
            }, { error ->
                listingView?.error()
                Timber.w(error)
                listingView?.hideProgressBar()
            })
        }
    }

    override fun loadNextPageNewRedditsList(after: String) {
        val observableReddits = dataManagerInterface?.getNextPageNewReddit(after)

        observableReddits?.let {
            observableReddits.concatMap({ dataWrappers ->
                getMaybeReddits(dataWrappers)
            }).subscribe({ reddit ->
                listingView?.loadNewReddits(reddit)
            }, { error ->
                Timber.w(error)
            })
        }
    }

    private fun getMaybeReddits(dataWrappers: RedditDataResponse): Maybe<MutableList<RedditNewsData>>? {
        val reddits = mutableListOf<RedditNewsData>()
        listingView?.saveNextPage(dataWrappers.data?.after ?: "")

        dataWrappers.data?.children?.forEach { redditChildrenDataNvl2Response ->
            redditChildrenDataNvl2Response.data?.let { reddits.add(it) }
        }
        return Maybe.just(reddits)
    }
}