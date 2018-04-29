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

interface StrictFragmentArguments<F, A>
        where A : FragmentArguments<F>, F : Fragment, F : StrictFragmentArguments<F, A>

inline fun <reified F : Fragment> getFragmentFor(args: FragmentArguments<F>) : F {
    return F::class.java.newInstance()?.also {
        val b = Bundle()
        b.putParcelable(FRAGMENT_ARGUMENTS, args)

        it.arguments = b
    } ?: throw IllegalArgumentException("${F::class} is not instantiable!")
}

val Fragment.strictArguments : Parcelable? get() = this.arguments?.getParcelable(FRAGMENT_ARGUMENTS)

inline fun <reified F, A : FragmentArguments<F>> F.strictArguments() : A
        where F : Fragment, F : StrictFragmentArguments<F, A> {
    return this.arguments?.getParcelable(FRAGMENT_ARGUMENTS)
            ?: throw IllegalArgumentException("Strict fragment arguments not found " +
                    "for ${F::class}. Try using getFragmentFor().")
}

const val FRAGMENT_ARGUMENTS = "com.ermerssso.strictextras.FRAGMENT_ARGUMENTS"