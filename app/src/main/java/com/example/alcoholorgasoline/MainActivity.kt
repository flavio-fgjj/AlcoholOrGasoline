package com.example.alcoholorgasoline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btn_calc)
        btn.setOnClickListener {
            besOption()
        }
    }

    private fun calcBestPrice(_alcoholPrice: String, _gasolinePrice: String) {
        val alcoholPrice = _alcoholPrice.toDouble()
        val gasolinePrice = _gasolinePrice.toDouble()

        val i = result.text.indexOf(":")
        val res = if ((alcoholPrice / gasolinePrice) < 0.7) "ALCOHOL" else "GASOLINE"

        if (i < 0) "The best option is: ".also { result.text = it }
        "${result.text.substring(0, i + 1)} $res".also { result.text = it }
    }

    private fun besOption() {
        val alcoholPrice = alcohol_price.text.toString()
        val gasolinePrice = gasoline_price.text.toString()

        if(alcoholPrice.isEmpty() || gasolinePrice.isEmpty()) {
            "Fill all fields!".also { result.text = it }
            return
        }

        calcBestPrice(alcoholPrice, gasolinePrice)
    }
}