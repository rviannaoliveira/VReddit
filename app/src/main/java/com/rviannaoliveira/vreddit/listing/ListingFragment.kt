package com.rviannaoliveira.vreddit.listing

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rviannaoliveira.vreddit.R
import com.rviannaoliveira.vreddit.RedditUtil
import com.rviannaoliveira.vreddit.modal.RedditNewsDataResponse
import kotlinx.android.synthetic.main.fragment_listing.*

/**
 * Criado por rodrigo on 18/10/17.
 */
class ListingFragment : Fragment(), ListingInterface.ListingView {

    private val listingPresenter = ListingPresenterImpl(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_listing, container, false)
    }

    override fun onResume() {
        super.onResume()
        listingPresenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        listingPresenter.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        listingPresenter.onDestroy()
    }

    override fun loadNewReddits(reddit: List<RedditNewsDataResponse>?) {
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE

    }

    override fun error() {
        RedditUtil.showErrorScreen(context, view)
    }

}