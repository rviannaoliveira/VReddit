package com.rviannaoliveira.vreddit.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rviannaoliveira.vreddit.listing.ListingActivity

/**
 * Criado por rodrigo on 20/10/17.
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, ListingActivity::class.java))
        finish()
    }
}