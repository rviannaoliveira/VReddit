package com.rviannaoliveira.vreddit.extensions

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.rviannaoliveira.vreddit.R


/**
 * Criado por rodrigo on 18/10/17.
 */

fun AppCompatActivity.showFragment(fragmentContainer: Int, fragment: Fragment) {
    supportFragmentManager
            .beginTransaction()
            .replace(fragmentContainer, fragment)
            .commitAllowingStateLoss()
}


fun AppCompatActivity.startActivityWithSlideTransictionOpenUpActivity(intent: Intent) {
    this.startActivity(intent)
    this.overridePendingTransition(R.animator.activity_open_up_translate, R.animator.activity_close_scale)
}

fun AppCompatActivity.closeActivityWithSlideTransictionCloseDownActivity() {
    this.overridePendingTransition(R.animator.activity_open_scale, R.animator.activity_close_down_translate)
}
