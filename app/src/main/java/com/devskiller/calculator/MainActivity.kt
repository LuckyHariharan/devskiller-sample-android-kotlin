package com.devskiller.calculator

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.devskiller.calculator.calculator.R

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val calculator = Calculator()
    private val display = Display()
    private lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        result = findViewById(R.id.result)
        findViewById<View>(R.id.ce).setOnClickListener(this)
        findViewById<View>(R.id.backspace).setOnClickListener(this)
        for (id in ButtonsMapping.MATH_OPERATIONS.keys) {
            findViewById<View>(id).setOnClickListener(this)
        }
        for (id in ButtonsMapping.DIGITS.keys) {
            findViewById<View>(id).setOnClickListener(this)
        }
    }

    override fun onClick(view: View) {
        // Place here the solution
    }

    private fun selectedDigit(view: View): Int? {
        return ButtonsMapping.DIGITS[view.id]
    }

    private fun selectedOperation(v: View): Operation? {
        return ButtonsMapping.MATH_OPERATIONS[v.id]
    }

    private fun refresh() {
        result.text = display.line
    }
}