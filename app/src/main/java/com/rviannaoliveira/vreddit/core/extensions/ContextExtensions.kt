package com.rviannaoliveira.vreddit.extensions

import android.content.Context
import android.content.Intent
import com.rviannaoliveira.vreddit.R

/**
 * Criado por rodrigo on 21/10/17.
 */

fun Context.sharedLink(url: String) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.putExtra(Intent.EXTRA_TEXT, url)
    intent.type = "text/plain"
    this.startActivity(Intent.createChooser(intent, this.getString(R.string.choose_a_option)))
}

fun Context.isTablet(): Boolean {
    return this.resources.getBoolean(R.bool.isTablet)
}

fun Context.isNotTablet(): Boolean {
    return !this.resources.getBoolean(R.bool.isTablet)
}