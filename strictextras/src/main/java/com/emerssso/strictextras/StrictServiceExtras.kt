@file:Suppress("unused")

package com.emerssso.strictextras

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Parcelable

/**
 * A marker interface that extends [Parcelable] and links the marked class with a particular
 * [Service] class, enabling the syntactic sugar of [startServiceWith].
 */
interface ServiceExtras<S : Service> : Parcelable

/**
 * Allows a [Service] to be started with statically defined extras bundle in the form of
 * a [ServiceExtras] data class.
 *
 * Example usage to start a PizzaService:
 *     context.startServiceWith(PizzaService.Extras( size = Size.SMALL, toppings = listOf())
 */
inline fun <reified S : Service, E : ServiceExtras<S>> Context.startServiceWith(extras: E) {
    val intent = Intent(this, S::class.java)
    intent.putExtra(SERVICE_EXTRAS, extras)
    startService(intent)
}

/**
 * Returns the [ServiceExtras] in an Intent's extras bundle. Returns null if not found.
 */
val Intent.serviceExtras : Parcelable? get() {
    return this.extras?.getParcelable(SERVICE_EXTRAS)
}

/**
 * The constant used to insert/extract extras using [startServiceWith] and [extras].
 */
const val SERVICE_EXTRAS = "com.emerssso.strictextras.SERVICE_EXTRAS"