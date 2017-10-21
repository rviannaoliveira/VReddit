package com.rviannaoliveira.vreddit.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rviannaoliveira.vreddit.R
import com.rviannaoliveira.vreddit.extensions.getTimeString
import com.rviannaoliveira.vreddit.extensions.sharedLink
import com.rviannaoliveira.vreddit.global.ConstantsParceable
import com.rviannaoliveira.vreddit.modal.RedditCommentData
import com.rviannaoliveira.vreddit.modal.RedditNewsData
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.news_default_layout.*

/**
 * Criado por rodrigo on 20/10/17.
 */
class DetailFragment : Fragment(), DetailInterface.DetailView {
    private var commentsAdapter: CommentsAdapter? = null
    private val detailPresenter = DetailPresenterImpl(this)

    companion object {
        fun newInstance(redditNewsData: RedditNewsData): DetailFragment {
            val bundle = Bundle()
            val fragment = DetailFragment()
            bundle.putParcelable(ConstantsParceable.SEND_BUNDLE_REDDIT_DATA, redditNewsData)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val redditNewsData: RedditNewsData? = arguments.getParcelable(ConstantsParceable.SEND_BUNDLE_REDDIT_DATA)

        redditNewsData?.let {
            setUI(redditNewsData)
            detailPresenter.onViewCreated(redditNewsData.id)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        detailPresenter.onDestroy()
    }

    private fun setUI(redditNewsData: RedditNewsData) {
        author_default.text = redditNewsData.author.plus(" - ").plus(redditNewsData.created.getTimeString())
        title_default.text = redditNewsData.title
        score_default.text = redditNewsData.score.toString()
        comment_default.text = redditNewsData.numComments.toString()
        share_default.setOnClickListener { context.sharedLink(redditNewsData.url) }
        description_default.text = redditNewsData.selftext
        image_item.visibility = View.GONE
        commentsAdapter = CommentsAdapter()
        recyclew_comments.adapter = commentsAdapter
        recyclew_comments.setHasFixedSize(true)
    }

    override fun loadComments(comments: List<RedditCommentData>) {
        if (comments.isNotEmpty()) {
            commentsAdapter?.setComments(comments)
        } else {
            error()
        }
    }

    override fun error() {
        txt_error_list_comments.visibility = View.VISIBLE
    }

    override fun showProgressBar() {
        progressBarDetail.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBarDetail.visibility = View.GONE
    }
}