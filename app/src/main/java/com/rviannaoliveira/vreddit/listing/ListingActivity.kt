package com.rviannaoliveira.vreddit.listing

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rviannaoliveira.vreddit.R
import com.rviannaoliveira.vreddit.extensions.showFragment

class ListingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        showFragment(R.id.fragment_container, ListingFragment.newInstance())
    }
}
