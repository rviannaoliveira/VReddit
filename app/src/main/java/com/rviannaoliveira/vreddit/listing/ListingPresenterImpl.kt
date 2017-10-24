package com.rviannaoliveira.vreddit.listing

import com.rviannaoliveira.vreddit.core.data.DataManagerFactory
import com.rviannaoliveira.vreddit.core.data.DataManagerInterface
import com.rviannaoliveira.vreddit.modal.NewsData
import com.rviannaoliveira.vreddit.modal.NewsDataResponse
import io.reactivex.Maybe
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

/**
 * Criado por rodrigo on 18/10/17.
 */
class ListingPresenterImpl(private var listingView: ListingInterface.ListingView?,
                           private var dataManagerInterface: DataManagerInterface? = DataManagerFactory.getDefaultInstance()) : ListingInterface.ListingPresenter {
    private val disposables = CompositeDisposable()

    override fun onViewCreated(connectedToInternet: Boolean) {
        listingView?.showProgressBar()
        loadNewRedditsList(connectedToInternet)
    }

    override fun onDestroy() {
        listingView = null
        dataManagerInterface = null
        disposables.dispose()
    }

    private fun loadNewRedditsList(connectedToInternet: Boolean) {
        val observableReddits = if (connectedToInternet) {
            dataManagerInterface?.getNewsReddits()?.concatMap({ dataWrappers ->
                getMaybeReddits(dataWrappers)
            })
        } else {
            dataManagerInterface?.getAllNewsLocal()
        }

        observableReddits?.let {
            disposables.add(
                    observableReddits.subscribe({ reddit ->
                        listingView?.loadNewReddits(reddit)
                        listingView?.hideProgressBar()
                    }, { error ->
                        listingView?.error()
                        Timber.w(error)
                        listingView?.hideProgressBar()
                    })
            )
        }

    }

    override fun loadNextPageNewRedditsList(after: String?) {
        after?.let {
            val observableReddits = dataManagerInterface?.getNextPageNewReddit(after)

            observableReddits?.let {
                disposables.add(observableReddits
                        .concatMap({ dataWrappers ->
                            getMaybeReddits(dataWrappers)
                        })
                        .subscribe({ reddit ->
                            listingView?.loadNewReddits(reddit)
                        }, { error ->
                            Timber.w(error)
                        })
                )
            }
        }
    }

    private fun getMaybeReddits(dataWrappers: NewsDataResponse): Maybe<MutableList<NewsData>>? {
        val reddits = mutableListOf<NewsData>()
        listingView?.saveNextPage(dataWrappers.data?.after ?: "")

        dataWrappers.data?.children?.forEach { redditChildrenDataNvl2Response ->
            redditChildrenDataNvl2Response.data?.let {
                reddits.add(it)
                dataManagerInterface?.insertNews(it)
            }
        }
        return Maybe.just(reddits)
    }
}