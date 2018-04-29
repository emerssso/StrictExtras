package com.emerssso.strictextras

import android.annotation.SuppressLint
import android.app.Application
import android.app.IntentService
import android.app.Service
import android.content.Intent
import junit.framework.TestCase
import kotlinx.android.parcel.Parcelize
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows

@RunWith(RobolectricTestRunner::class)
class StrictServiceExtrasTest {

    private val context : Application = RuntimeEnvironment.application

    @Test
    fun testStartActivityWith() {
        val extras = TestService.Extras("Test!")
        context.startServiceWith(extras)

        val startedIntent = Shadows.shadowOf(context).nextStartedService
        TestCase.assertTrue(Shadows.shadowOf(startedIntent).intentClass.isAssignableFrom(TestService::class.java))
        TestCase.assertEquals(extras, startedIntent.serviceExtras)
    }
}

class TestService : IntentService("Test") {

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Extras(val str: String) : ServiceExtras<TestService>

    override fun onHandleIntent(intent: Intent?) {
        //Do Nothing
    }
}