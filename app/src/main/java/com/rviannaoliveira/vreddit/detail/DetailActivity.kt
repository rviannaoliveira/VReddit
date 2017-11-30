package com.rviannaoliveira.vreddit.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.rviannaoliveira.vreddit.R
import com.rviannaoliveira.vreddit.core.extensions.closeActivityWithSlideTransictionCloseDownActivity
import com.rviannaoliveira.vreddit.core.extensions.showFragment
import com.rviannaoliveira.vreddit.core.global.ConstantsParceable
import com.rviannaoliveira.vreddit.core.model.NewsData

/**
 * Criado por rodrigo on 20/10/17.
 */
class DetailActivity : AppCompatActivity() {
    var animate: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val new: NewsData = intent.extras[ConstantsParceable.SEND_BUNDLE_REDDIT_DATA] as NewsData
        showFragment(R.id.fragment_container_list, DetailFragment.newInstance(new))
    }

    override fun onPause() {
        super.onPause()
        if (animate) {
            this.closeActivityWithSlideTransictionCloseDownActivity()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        animate = true
        super.onBackPressed()
    }
}