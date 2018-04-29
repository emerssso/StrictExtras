package com.emerssso.strictextras

import android.app.Activity
import android.content.Intent
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.android.parcel.Parcelize
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf

@RunWith(RobolectricTestRunner::class)
class StrictActivityExtrasTest {

    val context = RuntimeEnvironment.application

    @Test
    internal fun testStartActivityWith() {
        val extras = TestActivity.Extras("Test!")
        context.startActivityWith(extras)

        val startedIntent = shadowOf(context).nextStartedActivity
        assertTrue(shadowOf(startedIntent).intentClass.isAssignableFrom(TestActivity::class.java))
        assertEquals(extras, startedIntent.activityExtras)
    }

    @Test
    fun testExtrasAccessors() {
        val extras = TestActivity.Extras("Test!")

        val activity = Robolectric.buildActivity(TestActivity::class.java,
                Intent().putExtra(ACTIVITY_EXTRAS, extras))
                .create().start().get()

        assertEquals(extras, activity.extras)
        assertEquals(extras, activity.extras())
    }

    @Test(expected = IllegalArgumentException::class)
    fun testNullExtrasProperty() {
        val activity = Robolectric.buildActivity(TestActivity::class.java)
                .create().start().get()

        activity.extras
    }

    @Test(expected = IllegalArgumentException::class)
    fun testNullExtrasFunction() {
        val activity = Robolectric.buildActivity(TestActivity::class.java)
                .create().start().get()

        activity.extras()
    }
}

class TestActivity : Activity(), StrictExtras<TestActivity, TestActivity.Extras> {
    @Parcelize
    data class Extras(val str : String) : ActivityExtras<TestActivity>
}