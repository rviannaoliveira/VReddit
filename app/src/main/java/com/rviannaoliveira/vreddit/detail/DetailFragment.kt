package com.rviannaoliveira.vreddit.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rviannaoliveira.vreddit.R
import com.rviannaoliveira.vreddit.core.data.DataManager
import com.rviannaoliveira.vreddit.core.di.VRedditInjector
import com.rviannaoliveira.vreddit.core.extensions.*
import com.rviannaoliveira.vreddit.core.global.ConstantsParceable
import com.rviannaoliveira.vreddit.core.model.CommentData
import com.rviannaoliveira.vreddit.core.model.NewsData
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.news_default_layout_detail.*
import javax.inject.Inject


/**
 * Criado por rodrigo on 20/10/17.
 */
class DetailFragment : Fragment(), DetailInterface.DetailView {
    @Inject
    lateinit var dataManager: DataManager
    private lateinit var commentsAdapter: CommentsAdapter
    private lateinit var detailPresenter: DetailInterface.DetailPresenter

    companion object {
        fun newInstance(newsData: NewsData): DetailFragment {
            val bundle = Bundle()
            val fragment = DetailFragment()
            bundle.putParcelable(ConstantsParceable.SEND_BUNDLE_REDDIT_DATA, newsData)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        VRedditInjector.vRedditComponent.inject(this)
        detailPresenter = DetailPresenterImpl(this, dataManager)
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsData: NewsData? = arguments.getParcelable(ConstantsParceable.SEND_BUNDLE_REDDIT_DATA)

        newsData?.let {
            setUI(newsData)
            detailPresenter.onViewCreated(newsData.id, activity.isConnectedToInternet())
        }
    }

    override fun onDestroy() {
        detailPresenter.onDestroy()
        super.onDestroy()
    }

    private fun setUI(newsData: NewsData) {
        author_default_detail.text = newsData.author.plus(" - ").plus(newsData.created.getTimeString())
        title_default_detail.text = newsData.title
        score_default_detail.text = newsData.score.toString()
        comment_default_detail.text = newsData.numComments.toString()
        share_default_detail.setOnClickListener { activity.sharedLink(newsData.url) }
        description_default_detail.text = newsData.selftext
        btn_original_post.setOnClickListener { showCustomTab(newsData) }
        commentsAdapter = CommentsAdapter()
        recyclew_comments.adapter = commentsAdapter
        recyclew_comments.setHasFixedSize(true)
    }

    private fun showCustomTab(newsData: NewsData) {
        if (activity.isNotTablet()) {
            (activity as DetailActivity).animate = false
        }
        activity.showCustomTab(newsData.url)
    }

    override fun loadComments(comments: List<CommentData>) {
        if (comments.isNotEmpty()) {
            commentsAdapter.setComments(comments)
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