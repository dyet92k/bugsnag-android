package com.bugsnag.android

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ClientObservableTest {

    private val clientObservable = ClientObservable()

    @Test
    fun postOrientationChange() {
        clientObservable.addObserver { _, arg ->
            val msg = arg as StateEvent.UpdateOrientation
            assertEquals(90, msg.orientation)
        }
        clientObservable.postOrientationChange(90)
    }

    @Test
    fun postNdkInstall() {
        clientObservable.postNdkInstall(BugsnagTestUtils.generateImmutableConfig())
        clientObservable.addObserver { _, arg ->
            assertTrue(arg is StateEvent.Install)
        }
    }

    @Test
    fun postNdkDeliverPending() {
        clientObservable.postNdkDeliverPending()
        clientObservable.addObserver { _, arg ->
            assertTrue(arg is StateEvent.DeliverPending)
        }
    }
}
