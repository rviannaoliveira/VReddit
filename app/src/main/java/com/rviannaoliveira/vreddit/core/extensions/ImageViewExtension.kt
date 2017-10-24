package com.rviannaoliveira.vreddit.extensions

import android.widget.ImageView
import com.rviannaoliveira.vreddit.R
import com.squareup.picasso.Picasso

/**
 * Criado por rodrigo on 19/10/17.
 */

fun ImageView.loadImage(url: String) {
    Picasso.with(context)
            .load(url)
            .fit()
            .error(R.drawable.image_empty)
            .placeholder(R.drawable.image_empty)
            .into(this)
}