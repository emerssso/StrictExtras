@file:Suppress("unused")

package com.emerssso.strictextras

import android.app.Fragment
import android.os.Bundle
import android.os.Parcelable

/**
 * Allows similar syntax for [Fragment] instantiation with correctly configured arguments
 * as with [StrictActivityExtras].
 */
interface FragmentArguments<F : Fragment> : Parcelable

/**
 * Mark a Fragment as expecting [FragmentArguments] in order to make use of the
 *  typed [strictArguments] function
 */
interface StrictFragmentArguments<F, A>
        where A : FragmentArguments<F>, F : Fragment, F : StrictFragmentArguments<F, A>

/**
 * Returns an instance of the [F] [Fragment] with the passed [args] set as [FragmentArguments].
 */
inline fun <reified F : Fragment> getFragmentFor(args: FragmentArguments<F>) : F {
    return F::class.java.newInstance()?.also {
        val b = Bundle()
        b.putParcelable(FRAGMENT_ARGUMENTS, args)

        it.arguments = b
    } ?: throw IllegalArgumentException("${F::class} is not instantiable!")
}

/** Get the [FragmentArguments] as a [Parcelable] for this [Fragment], or null if not available */
val Fragment.strictArguments : Parcelable? get() = this.arguments?.getParcelable(FRAGMENT_ARGUMENTS)

/**
 * Get the [FragmentArguments] for this [Fragment].
 *
 * @throws IllegalArgumentException if the [FragmentArguments] aren't available (usually means
 * the fragment was not created correctly)
 */
inline fun <reified F, A : FragmentArguments<F>> F.strictArguments() : A
        where F : Fragment, F : StrictFragmentArguments<F, A> {
    return this.arguments?.getParcelable(FRAGMENT_ARGUMENTS)
            ?: throw IllegalArgumentException("Strict fragment arguments not found " +
                    "for ${F::class}. Try using getFragmentFor().")
}

/** Constant [Bundle] tag used to store [FragmentArguments] by [getFragmentFor] */
const val FRAGMENT_ARGUMENTS = "com.ermerssso.strictextras.FRAGMENT_ARGUMENTS"