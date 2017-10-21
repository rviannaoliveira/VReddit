package com.rviannaoliveira.vreddit.detail

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rviannaoliveira.vreddit.R
import com.rviannaoliveira.vreddit.extensions.getTimeString
import com.rviannaoliveira.vreddit.modal.RedditCommentData

/**
 * Criado por rodrigo on 21/10/17.
 */
class CommentsAdapter : RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {
    private var comments = mutableListOf<RedditCommentData>()
    private lateinit var context: Context

    fun setComments(comments: List<RedditCommentData>) {
        this.comments.addAll(comments)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        if (comments.isNotEmpty()) {
            val comment = comments[position]
            holder.author.text = "[".plus(comment.author).plus(" - ").plus(comment.created.getTimeString()).plus("]")
            holder.body.text = comment.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        this.context = parent.context
        return CommentsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_comments, parent, false))
    }

    override fun getItemCount(): Int = comments.size


    inner class CommentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var author: TextView = itemView.findViewById(R.id.author_comment)
        var body: TextView = itemView.findViewById(R.id.body_comment)
    }
}