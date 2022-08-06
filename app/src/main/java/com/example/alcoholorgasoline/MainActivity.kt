package com.example.alcoholorgasoline

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
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

    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val hideKey = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hideKey.hideSoftInputFromWindow(view.windowToken, 0)
        } else {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        }
    }

    private fun calcBestPrice(_alcoholPrice: String, _gasolinePrice: String) {
        val alcoholPrice = _alcoholPrice.toDouble()
        val gasolinePrice = _gasolinePrice.toDouble()

        val i = result.text.indexOf(":")
        val res = if ((alcoholPrice / gasolinePrice) < 0.7) "ALCOHOL" else "GASOLINE"

        if (!result.text.contains(":")) "The best option is: ".also { result.text = it }
        "${result.text.substring(0, i + 1)} $res".also { result.text = it }
    }

    private fun besOption() {
        val alcoholPrice = alcohol_price.text.toString()
        val gasolinePrice = gasoline_price.text.toString()

        if(alcoholPrice.isEmpty() || gasolinePrice.isEmpty()) {
            "All fields need to be filled!".also { result.text = it }
            return
        }

        calcBestPrice(alcoholPrice, gasolinePrice)
        hideKeyboard()
    }
}