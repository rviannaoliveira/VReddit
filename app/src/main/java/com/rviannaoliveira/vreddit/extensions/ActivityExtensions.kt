package com.rviannaoliveira.vreddit.extensions

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Criado por rodrigo on 18/10/17.
 */

fun AppCompatActivity.showFragment(fragmentContainer: Int, fragment: Fragment) {
    supportFragmentManager
            .beginTransaction()
            .replace(fragmentContainer, fragment)
            .commitAllowingStateLoss()
}