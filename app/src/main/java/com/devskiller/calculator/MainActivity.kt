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
        // Check if the button is a digit
        val digit = selectedDigit(view)
        if (digit != null) {
            display.insertDigit(digit)
            refresh()
            return
        }

        // Check if the button is an operation
        val operation = selectedOperation(view)
        if (operation != null) {
            if (operation != Operation.NONE) {
                // Evaluate the current value with the selected operation
                val currentValue = display.getValue()
                val result = calculator.evaluate(currentValue)
                display.setValue(result)
                calculator.operation = operation
            } else {
                // Equals operation: finalize the calculation
                val result = calculator.evaluate(display.getValue())
                display.setValue(result)
                calculator.clear() // Reset calculator after operation
            }
            refresh()
            return
        }

        // Handle special buttons like CE and backspace
        when (view.id) {
            R.id.ce -> {
                display.clear()
                calculator.clear()
                refresh()
            }
            R.id.backspace -> {
                display.removeDigit()
                refresh()
            }
        }
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