package com.rviannaoliveira.vreddit.detail

import android.os.SystemClock
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.scrollTo
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import com.rviannaoliveira.vreddit.R
import com.rviannaoliveira.vreddit.TestUtil
import com.rviannaoliveira.vreddit.listing.NewsAdapter
import org.hamcrest.CoreMatchers.endsWith


/**
 * Criado por rodrigo on 22/10/17.
 */
class RoboDetail {
    fun clickItem(position: Int): RoboDetail {
        SystemClock.sleep(200)
        onView(withId(R.id.recyclew_posts))
                .perform(RecyclerViewActions.actionOnItemAtPosition<NewsAdapter.NewsViewHolder>(position,
                        TestUtil.clickChildViewWithId(R.id.card_view_new)))
        return this
    }

    fun verifyTextComplete(text: String): RoboDetail {
        onView(withId(R.id.recyclew_comments)).perform(scrollTo())
        onView(ViewMatchers.withId(R.id.description_default_detail)).check(matches(withText(endsWith(text))))
        return this
    }

    fun verifyRecyclerViewVisible() {
        onView(withId(R.id.recyclew_comments)).perform(scrollTo())
        onView(ViewMatchers.withId(R.id.recyclew_comments)).perform(
                RecyclerViewActions.scrollToPosition<NewsAdapter.NewsViewHolder>(0))
                .check(matches(isDisplayed()))
    }

    fun verifyMessageEmpty() {
        onView(withId(R.id.txt_error_list_comments)).perform(scrollTo()).check(matches(isDisplayed()))
    }
}