package com.rviannaoliveira.vreddit.core.di

import com.rviannaoliveira.vreddit.listing.ListingFragment
import dagger.Component

/**
 * Criado por rodrigo on 29/11/17.
 */
@Component(modules = arrayOf(ApplicationModule::class,NetworkModule::class,ServiceModule::class))
interface VRedditComponent {
    fun inject(listingFragment: ListingFragment)
}