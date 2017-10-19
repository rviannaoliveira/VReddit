package com.rviannaoliveira.vreddit.listing

import com.rviannaoliveira.vreddit.DataManagerInterface
import com.rviannaoliveira.vreddit.data.DataManagerFactory
import timber.log.Timber

/**
 * Criado por rodrigo on 18/10/17.
 */
class ListingPresenterImpl(private var listingView: ListingInterface.ListingView?,
                           private var dataManagerInterface: DataManagerInterface? = DataManagerFactory.getDefaultInstance()) : ListingInterface.ListingPresenter {


    override fun onResume() {
        listingView?.showProgressBar()
        loadNewRedditsList()
    }

    override fun onPause() {

    }

    override fun onDestroy() {
        listingView = null
        dataManagerInterface = null
    }

    private fun loadNewRedditsList() {
        val observableReddits = dataManagerInterface?.getNewReddit()

        observableReddits?.subscribe({ reddit ->
            listingView?.loadNewReddits(reddit)
            listingView?.hideProgressBar()
        }, { error ->
            listingView?.error()
            Timber.w(error)
            listingView?.hideProgressBar()
        })
    }
}