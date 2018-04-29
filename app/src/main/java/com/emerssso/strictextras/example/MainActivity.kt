package com.emerssso.strictextras.example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.emerssso.strictextras.R
import com.example.strictextras.startActivityWith
import org.jetbrains.anko.button
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.verticalLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {

            button(R.string.start_activty) {
                onClick {
                    startActivityWith(PizzaActivity.Extras(
                            size = Size.SMALL,
                            toppings = listOf(Topping.PEPPERONI)))
                }
            }
        }
    }
}