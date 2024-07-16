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
        selectedDigit(view)?.let {
            // Insert digit to the display
            display.insertDigit(it)
            refresh()
            return
        }

        // Check if the button is an operation
        selectedOperation(view)?.let {
            // Evaluate the current value with the selected operation
            calculator.evaluate(display.getValue())
            // Set the selected operation
            calculator.operation = it
            // Clear the display if necessary
            if (it == Operation.NONE) display.clear()
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