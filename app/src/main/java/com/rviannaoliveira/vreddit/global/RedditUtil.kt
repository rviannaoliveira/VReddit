package com.rviannaoliveira.vreddit.global

import android.content.Context
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import com.rviannaoliveira.vreddit.R

/**
 * Criado por rodrigo on 18/10/17.
 */
object RedditUtil {

    fun showCustomTab(context: Context, url: String) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
        builder.addDefaultShareMenuItem()
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }
}