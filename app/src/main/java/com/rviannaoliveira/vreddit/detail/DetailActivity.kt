package com.rviannaoliveira.vreddit.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.rviannaoliveira.vreddit.R
import com.rviannaoliveira.vreddit.extensions.showFragment
import com.rviannaoliveira.vreddit.global.ConstantsParceable
import com.rviannaoliveira.vreddit.modal.RedditNewsData

/**
 * Criado por rodrigo on 20/10/17.
 */
class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val redditNew: RedditNewsData = intent.extras[ConstantsParceable.SEND_BUNDLE_REDDIT_DATA] as RedditNewsData
        showFragment(R.id.fragment_container, DetailFragment.newInstance(redditNew))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}