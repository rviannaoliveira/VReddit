package com.rviannaoliveira.vreddit.listing

import android.content.Intent
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.runner.AndroidJUnit4
import com.rviannaoliveira.vreddit.initMockServer
import com.rviannaoliveira.vreddit.main.MainActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Criado por rodrigo on 22/10/17.
 */
@RunWith(AndroidJUnit4::class)
class ListingFragmentTest {
    @get:Rule
    private val activityRule = IntentsTestRule(MainActivity::class.java, true, false)

    private lateinit var robo: RoboListing
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        robo = RoboListing()
        server = MockWebServer()
        server.initMockServer()
        activityRule.launchActivity(Intent())
    }

    @Test
    fun navigation_in_the_posts_lists_using_pagination() {
        robo.goToIndex(2)
                .goToIndex(4)
                .goToIndex(6)
                .goToIndex(7)
                .goToIndex(3)
                .loadFirstNexPage()
                .goToIndex(2)
                .goToIndex(13)
    }

    @Test
    @Throws(Exception::class)
    fun filter_listing_according_with_title() {
        robo.clickFilter()
                .writeFilter("Here's Wha")
                .verifyItemAppeared("Here's What Happens When You Say \"Ok, Google Let's Get Spooky\" In a Smart Home")
                .clearFilter()
                .writeFilter("Check")
                .verifyItemAppeared("Check your Pixel 2XL orders - mine was cancelled with no warning")
    }

    @Test
    @Throws(Exception::class)
    fun click_item_to_open_detail() {
        robo.clickItem(0)
                .verifyOpenDetailActivity(activityRule.activity)
                .clickItemArrowMenu()
    }

    @After
    fun finish() {
        Intents.release()
    }

}