package com.rviannaoliveira.vreddit.global

import android.util.Log
import timber.log.Timber

/**
 * Criado por rodrigo on 22/10/17.
 */
class VRedditTimber : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        return super.createStackElementTag(element) + "|" + element.lineNumber
    }

    override fun log(priority: Int, tag: String?, message: String?, t: Throwable?) {
        Log.println(priority, "VReddit" + "|" + tag, message)
    }
}