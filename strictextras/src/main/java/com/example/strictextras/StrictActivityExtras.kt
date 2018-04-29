@file:Suppress("unused")

package com.example.strictextras

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Parcelable

/**
 * A marker interface that extends [Parcelable] and links the marked class with a particular
 * [Activity] class, enabling the syntactic sugar of [startActivityWith].
 */
interface ActivityExtras<A : Activity> : Parcelable

/**
 * Optional marker interface for [Activity] classes making use of [ActivityExtras].
 * Allows [extras] to be used without a cast by making type information for extras available
 * at runtime.
 */
interface StrictExtras<A, E> where E : ActivityExtras<A>, A : Activity, A : StrictExtras<A, E>

/**
 * Allows an [Activity] to be started with statically defined extras bundle in the form of
 * an [ActivityExtras] data class.
 *
 * Example usage to start a PizzaActivity:
 *     context.startActivityWith(PizzaActivity.Extras( size = Size.SMALL, toppings = listOf())
 */
inline fun <reified A : Activity, E : ActivityExtras<A>> Context.startActivityWith(extras: E) {
    val intent = Intent(this, A::class.java)
    intent.putExtra(ACTIVITY_EXTRAS, extras)
    startActivity(intent)
}

/**
 * An accessor function for [ActivityExtras] included on an [Activity] using
 * [Context.startActivityWith].In order to avoid a cast on access, your [Activity] must implement the
 * marker interface [StrictExtras] to use this extension function.
 */
inline fun <reified A, E : ActivityExtras<A>>  A.extras() : E
        where A : Activity, A : StrictExtras<A, E> {
    return intent.extras.getParcelable(ACTIVITY_EXTRAS)
            ?: throw IllegalArgumentException("Parameters not set for $this. " +
                    "Try using Context.startActivityWith().")
}

/**
 * An accessor property for [ActivityExtras] included on an activity using
 * [Context.startActivityWith]. Because generics can't be used for property receivers,
 * you will have to cast accesses to the correct type using 'as', i.e. extras as MyExtras.
 */
val Activity.extras : Parcelable get() {
    return this.intent.extras.getParcelable(ACTIVITY_EXTRAS)
            ?: throw IllegalArgumentException("Parameters not set for $this. " +
                    "Try using Context.startActivityWith().")
}

/**
 * The constant used to insert/extract extras using [startActivityWith] and [extras].
 */
const val ACTIVITY_EXTRAS = "com.emerssso.strictextras.ACTIVITY_EXTRAS"