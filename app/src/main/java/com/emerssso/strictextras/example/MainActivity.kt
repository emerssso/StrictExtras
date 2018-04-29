package com.emerssso.strictextras.example

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.emerssso.strictextras.startActivityWith
import com.emerssso.strictextras.startServiceWith
import org.jetbrains.anko.button
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.verticalLayout

/**
 * Entry class that demonstrates how to use StrictExtras APIs to start other components
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Intent(this, PizzaActivity::class.java)

        verticalLayout {

            button(R.string.start_activity) {
                onClick {
                    startActivityWith(PizzaActivity.Extras(
                            size = Size.SMALL,
                            toppings = listOf(Topping.PEPPERONI)))
                }
            }

            button(R.string.start_service) {
                onClick {
                    startServiceWith(PizzaIntentService.Extras(
                            size = Size.LARGE,
                            toppings = listOf(Topping.HAM, Topping.OLIVE)
                    ))
                }
            }
        }
    }
}