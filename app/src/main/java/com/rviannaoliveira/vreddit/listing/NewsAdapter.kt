package com.rviannaoliveira.vreddit.listing

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.rviannaoliveira.vreddit.R
import com.rviannaoliveira.vreddit.detail.DetailActivity
import com.rviannaoliveira.vreddit.extensions.getTimeString
import com.rviannaoliveira.vreddit.extensions.loadImage
import com.rviannaoliveira.vreddit.extensions.sharedLink
import com.rviannaoliveira.vreddit.global.ConstantsParceable.SEND_BUNDLE_REDDIT_DATA
import com.rviannaoliveira.vreddit.modal.RedditNewsData
import com.rviannaoliveira.vreddit.util.RedditUtil


/**
 * Criado por rodrigo on 19/10/17.
 */
class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var showLoader: Boolean = false
    private var news = mutableListOf<RedditNewsData>()
    private var newsOriginal = mutableListOf<RedditNewsData>()
    private lateinit var context: Context

    companion object {
        private val VIEW_ITEM = 1
        private val VIEW_LOADER = 2
    }

    fun setNews(news: List<RedditNewsData>) {
        this.newsOriginal.addAll(news)
        this.news.addAll(news)
        notifyDataSetChanged()
    }


    override fun getItemViewType(position: Int): Int {
        if (news.size == newsOriginal.size && position != 0 && position == itemCount - 1) {
            return VIEW_LOADER
        }
        return VIEW_ITEM
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (news.isNotEmpty()) {
            if (holder is NewsViewHolder) {
                val new = news[position]
                holder.author.text = new.author
                holder.time.text = new.created.getTimeString()
                holder.score.text = new.score.toString()
                holder.comments.text = new.numComments.toString()
                holder.share.setOnClickListener { context.sharedLink(new.url) }
                setTitle(new.title, holder.title)
                setClickListenerItem(new, holder.cardView)
                setDescription(new.selftext, holder.description)
                setImage(new.thumbnail, holder.image)
            } else {
                (holder as LoaderViewHolder).progressBar.visibility = View.VISIBLE
            }
        }
    }

    private fun setTitle(title: String, textViewTitle: TextView) {
        textViewTitle.ellipsize = TextUtils.TruncateAt.END
        textViewTitle.maxLines = 3
        textViewTitle.text = title
    }

    private fun setClickListenerItem(new: RedditNewsData, cardView: CardView) {
        cardView.setOnClickListener {
            if (new.selftext.isEmpty()) {
                RedditUtil.showCustomTab(context, new.url)
            } else {
                val detailIntent = Intent(context, DetailActivity::class.java)
                detailIntent.putExtra(SEND_BUNDLE_REDDIT_DATA, new)
                context.startActivity(detailIntent)
            }
        }
    }

    private fun setDescription(selftext: String, description: TextView) {
        if (selftext.isNotEmpty()) {
            description.ellipsize = TextUtils.TruncateAt.END
            description.maxLines = 2
            description.text = selftext
        }
    }

    private fun setImage(thumbnail: String, image: ImageView) {
        if (!thumbnail.contains("http") || thumbnail.isEmpty()) {
            image.visibility = View.GONE
        } else {
            image.loadImage(thumbnail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        this.context = parent.context
        return if (viewType == VIEW_ITEM) {
            NewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false))
        } else {
            LoaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.loader_item_layout, parent, false))
        }
    }

    override fun getItemCount(): Int = news.size

    fun showLoading(status: Boolean) {
        showLoader = status
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var author: TextView = itemView.findViewById(R.id.author_default)
        var time: TextView = itemView.findViewById(R.id.time_default)
        var title: TextView = itemView.findViewById(R.id.title_default)
        var image: ImageView = itemView.findViewById(R.id.image_item)
        var score: TextView = itemView.findViewById(R.id.score_default)
        var comments: TextView = itemView.findViewById(R.id.comment_default)
        var share: TextView = itemView.findViewById(R.id.share_default)
        var description: TextView = itemView.findViewById(R.id.description_default)
        var cardView: CardView = itemView.findViewById(R.id.card_view_new)
    }


    inner class LoaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var progressBar: ProgressBar = itemView.findViewById(R.id.progressbar)
    }


    fun filter(text: String?) {
        news.clear()
        text?.let {
            if (it.isEmpty()) {
                news.addAll(newsOriginal)
            } else {
                newsOriginal
                        .filter { it.title.contains(text, true) }
                        .map { news.add(it) }

            }
            notifyDataSetChanged()
        }
    }
}