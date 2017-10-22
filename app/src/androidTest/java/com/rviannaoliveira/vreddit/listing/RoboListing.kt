package com.rviannaoliveira.vreddit.listing

import android.content.ComponentName
import android.content.Context
import android.os.SystemClock
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withContentDescription
import com.rviannaoliveira.vreddit.R
import com.rviannaoliveira.vreddit.TestUtil
import com.rviannaoliveira.vreddit.data.api.RestApiDataSource
import com.rviannaoliveira.vreddit.detail.DetailActivity


/**
 * Criado por rodrigo on 22/10/17.
 */
class RoboListing {


    fun goToIndex(index: Int): RoboListing {
        onView(ViewMatchers.withId(R.id.recyclew_posts)).perform(
                RecyclerViewActions.scrollToPosition<NewsAdapter.NewsViewHolder>(index))
        return this
    }

    fun loadFirstNexPage(): RoboListing {
        onView(ViewMatchers.withId(R.id.recyclew_posts)).perform(
                RecyclerViewActions.scrollToPosition<NewsAdapter.NewsViewHolder>(RestApiDataSource.LIMIT_NEWS - 1))
        return this
    }

    fun clickFilter(): RoboListing {
        onView(ViewMatchers.withId(R.id.menu_search)).perform(ViewActions.click())
        return this
    }

    fun writeFilter(text: String): RoboListing {
        onView(ViewMatchers.withId(R.id.search_src_text)).perform(ViewActions.typeText(text), ViewActions.closeSoftKeyboard())
        return this
    }

    fun verifyItemAppeared(text: String): RoboListing {
        onView(ViewMatchers.withText(text)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        return this
    }

    fun clearFilter(): RoboListing {
        onView(ViewMatchers.withId(R.id.search_src_text)).perform(ViewActions.clearText(), ViewActions.closeSoftKeyboard())
        return this
    }

    fun verifyOpenDetailActivity(context: Context): RoboListing {
        intended(hasComponent(ComponentName(context, DetailActivity::class.java)))
        return this
    }

    fun clickItem(position: Int): RoboListing {
        SystemClock.sleep(200)
        onView(ViewMatchers.withId(R.id.recyclew_posts))
                .perform(RecyclerViewActions.actionOnItemAtPosition<NewsAdapter.NewsViewHolder>(position,
                        TestUtil.clickChildViewWithId(R.id.card_view_new)))
        return this
    }

    fun clickItemArrowMenu(): RoboListing {
        onView(withContentDescription("Navigate up")).perform(click())
        return this
    }
}