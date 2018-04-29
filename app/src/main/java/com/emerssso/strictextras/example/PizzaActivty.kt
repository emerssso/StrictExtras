package com.emerssso.strictextras.example

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.emerssso.strictextras.ActivityExtras
import com.emerssso.strictextras.StrictActivityExtras
import com.emerssso.strictextras.extras
import kotlinx.android.parcel.Parcelize
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout

class PizzaActivity : AppCompatActivity(), StrictActivityExtras<PizzaActivity, PizzaActivity.Extras> {

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Extras(val toppings: List<Topping>, val size: Size) : ActivityExtras<PizzaActivity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pizza = extras()

        verticalLayout {
            textView("${pizza.size} pizza with ${pizza.toppings}")
        }
    }
}

enum class Topping { PEPPERONI, HAM, ONION, OLIVE }
enum class Size { SMALL, MEDIUM, LARGE, EXTRA_LARGE }
