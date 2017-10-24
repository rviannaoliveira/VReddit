package com.rviannaoliveira.vreddit.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import com.rviannaoliveira.vreddit.AppApplication
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

    fun isConnectedToInternet(): Boolean {
        if (AppApplication.URL != null) {
            return true
        }
        val connectivityManager = AppApplication.appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

}