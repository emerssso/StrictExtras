package com.emerssso.strictextras.example

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.emerssso.strictextras.ServiceExtras
import com.emerssso.strictextras.serviceExtras
import kotlinx.android.parcel.Parcelize

private const val TAG = "PizzaIntentService"

/**
 * An example of a really limited [IntentService] usage of Strict Extras for a service
 * Note that since the starting [Intent] for a service isn't a property, we have to get the
 * extras from the intent, unlike with [PizzaActivity]
 */
class PizzaIntentService : IntentService(TAG) {

    @Parcelize
    data class Extras(val size: Size, val toppings: List<Topping>) :
            ServiceExtras<PizzaIntentService>

    override fun onHandleIntent(intent: Intent?) {
        val pizza = intent?.serviceExtras as Extras?

        pizza?.let {
            Log.d(TAG, "${pizza.size} pizza with ${pizza.toppings}")
        }
                ?: Log.w(TAG, "No pizza sent!")

    }
}
