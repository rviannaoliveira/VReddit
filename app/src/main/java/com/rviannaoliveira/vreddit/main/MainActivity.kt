package com.rviannaoliveira.vreddit.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rviannaoliveira.vreddit.R
import com.rviannaoliveira.vreddit.core.extensions.isNotTablet
import com.rviannaoliveira.vreddit.core.extensions.isTablet
import com.rviannaoliveira.vreddit.core.extensions.showFragment
import com.rviannaoliveira.vreddit.core.extensions.startActivityWithSlideTransictionOpenUpActivity
import com.rviannaoliveira.vreddit.core.global.ConstantsParceable
import com.rviannaoliveira.vreddit.core.model.NewsData
import com.rviannaoliveira.vreddit.detail.DetailActivity
import com.rviannaoliveira.vreddit.detail.DetailFragment
import com.rviannaoliveira.vreddit.listing.ListingFragment

class MainActivity : AppCompatActivity(), OnItemRedditSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (isNotTablet()) {
            showFragment(R.id.fragment_container_list, ListingFragment.newInstance())
        }
    }

    override fun onItemSelected(new: NewsData) {
        if (isTablet()) {
            showFragment(R.id.fragment_container_detail, DetailFragment.newInstance(new))
        } else {
            val detailIntent = Intent(this, DetailActivity::class.java)
            detailIntent.putExtra(ConstantsParceable.SEND_BUNDLE_REDDIT_DATA, new)
            this.startActivityWithSlideTransictionOpenUpActivity(detailIntent)
        }
    }
}
