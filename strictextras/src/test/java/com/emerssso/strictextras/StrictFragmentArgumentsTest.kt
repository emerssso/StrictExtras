package com.emerssso.strictextras

import android.app.Fragment
import junit.framework.Assert.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.android.parcel.Parcelize
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class StrictFragmentArgumentsTest {
    @Test
    fun testGetFragmentFor() {
        val args = TestFragment.Args("Test!")
        val fragment = getFragmentFor(args)

        assertNotNull(fragment)
        assertEquals(args, fragment.strictArguments)
        assertEquals(args, fragment.strictArguments())
    }
}

class TestFragment : Fragment(), StrictFragmentArguments<TestFragment, TestFragment.Args> {
    @Parcelize
    data class Args(val str: String) : FragmentArguments<TestFragment>
}