package com.rviannaoliveira.vreddit

import android.support.test.espresso.Espresso
import com.squareup.rx2.idler.Rx2Idler
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.ExternalResource


/**
 * Criado por rodrigo on 22/10/17.
 */
class Rx2IdlingSchedulerRule : ExternalResource() {
    @Throws(Throwable::class)
    override fun before() {
        val ioIdlingScheduler = Rx2Idler.wrap(Schedulers.io(), "RxJava2 Io Idling Scheduler")
        val computationIdlingScheduler = Rx2Idler.wrap(Schedulers.io(), "RxJava2 Computation Idling Scheduler")
        val newThreadIdlingScheduler = Rx2Idler.wrap(Schedulers.io(), "RxJava2 New Thread Idling Scheduler")

        Espresso.registerIdlingResources(
                ioIdlingScheduler, computationIdlingScheduler, newThreadIdlingScheduler
        )

        RxJavaPlugins.setIoSchedulerHandler { ioIdlingScheduler }
        RxJavaPlugins.setInitComputationSchedulerHandler { computationIdlingScheduler }
        RxJavaPlugins.setComputationSchedulerHandler { computationIdlingScheduler }
        RxJavaPlugins.setNewThreadSchedulerHandler { newThreadIdlingScheduler }
    }

    override fun after() {
        RxJavaPlugins.reset()
    }

}