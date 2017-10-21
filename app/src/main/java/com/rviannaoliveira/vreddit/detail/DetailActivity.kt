package com.rviannaoliveira.vreddit.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rviannaoliveira.vreddit.R
import com.rviannaoliveira.vreddit.extensions.showFragment

/**
 * Criado por rodrigo on 20/10/17.
 */
class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        showFragment(R.id.fragment_container, DetailFragment())
    }
}