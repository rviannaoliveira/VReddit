package com.rviannaoliveira.vreddit.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rviannaoliveira.vreddit.R
import com.rviannaoliveira.vreddit.modal.RedditCommentData
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_listing.*

/**
 * Criado por rodrigo on 20/10/17.
 */
class DetailFragment : Fragment(), DetailInterface.DetailView {
    private var commentsAdapter: CommentsAdapter? = null
    private val detailPresenter = DetailPresenterImpl(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUI()
        detailPresenter.onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        detailPresenter.onDestroy()
    }

    private fun setUI() {
        commentsAdapter = CommentsAdapter()
        recyclew_comments.adapter = commentsAdapter
        recyclew_comments.setHasFixedSize(true)
    }

    override fun loadComments(comments: List<RedditCommentData>) {
        commentsAdapter?.setComments(comments)
    }

    override fun error() {
        include_problem_screen_detail.visibility = View.VISIBLE
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }
}