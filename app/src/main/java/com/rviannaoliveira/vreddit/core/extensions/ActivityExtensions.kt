package com.rviannaoliveira.vreddit.core.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.rviannaoliveira.vreddit.AppApplication
import com.rviannaoliveira.vreddit.R


/**
 * Criado por rodrigo on 18/10/17.
 */

fun AppCompatActivity.showFragment(fragmentContainer: Int, fragment: Fragment) {
    supportFragmentManager
            .beginTransaction()
            .replace(fragmentContainer, fragment)
            .commitAllowingStateLoss()
}

fun Activity.sharedLink(url: String) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.putExtra(Intent.EXTRA_TEXT, url)
    intent.type = "text/plain"
    this.startActivity(Intent.createChooser(intent, this.getString(R.string.choose_a_option)))
}


fun Activity.showCustomTab(url: String) {
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary))
    builder.addDefaultShareMenuItem()
    customTabsIntent.launchUrl(this, Uri.parse(url))
}

fun Activity.isConnectedToInternet(): Boolean {
    if (AppApplication.URL != null) {
        return true
    }
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting
}


fun AppCompatActivity.startActivityWithSlideTransictionOpenUpActivity(intent: Intent) {
    this.startActivity(intent)
    this.overridePendingTransition(R.animator.activity_open_up_translate, R.animator.activity_close_scale)
}

fun AppCompatActivity.closeActivityWithSlideTransictionCloseDownActivity() {
    this.overridePendingTransition(R.animator.activity_open_scale, R.animator.activity_close_down_translate)
}


fun Activity.isTablet(): Boolean {
    return this.resources.getBoolean(R.bool.isTablet)
}

fun Activity.isNotTablet(): Boolean {
    return !this.resources.getBoolean(R.bool.isTablet)
}
