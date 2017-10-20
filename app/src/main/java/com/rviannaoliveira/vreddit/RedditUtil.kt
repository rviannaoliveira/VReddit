package com.rviannaoliveira.vreddit

import android.content.Context
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView

/**
 * Criado por rodrigo on 18/10/17.
 */
object RedditUtil {

    fun showErrorScreen(context: Context, view: View?) {
        val includeProblem = view?.findViewById<View>(R.id.include_problem_screen)
        val textProblem = view?.findViewById<TextView>(R.id.text_problem) as TextView

        includeProblem?.visibility = View.VISIBLE
        textProblem.text = context.getString(R.string.problem_generic)
        textProblem.setTextColor(ContextCompat.getColor(context, R.color.textColorPrimary))
    }

    fun showCustomTab(context: Context, url: String) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
        builder.addDefaultShareMenuItem()
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }
}