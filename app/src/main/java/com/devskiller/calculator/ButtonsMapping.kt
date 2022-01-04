package com.devskiller.calculator

import android.annotation.SuppressLint
import com.devskiller.calculator.calculator.R
import java.util.*

@SuppressLint("UseSparseArrays")
object ButtonsMapping {
    val MATH_OPERATIONS: MutableMap<Int, Operation> = HashMap()
    val DIGITS: MutableMap<Int, Int> = HashMap()

    init {
        MATH_OPERATIONS[R.id.btnEqual] = Operation.NONE
        MATH_OPERATIONS[R.id.btnAdd] = Operation.ADD
        MATH_OPERATIONS[R.id.btnSubstract] = Operation.SUBTRACT
        MATH_OPERATIONS[R.id.btnMultiply] =
            Operation.MULTIPLY
        MATH_OPERATIONS[R.id.btnDivide] = Operation.DIVIDE
        DIGITS[R.id.btn0] = 0
        DIGITS[R.id.btn1] = 1
        DIGITS[R.id.btn2] = 2
        DIGITS[R.id.btn3] = 3
        DIGITS[R.id.btn4] = 4
        DIGITS[R.id.btn5] = 5
        DIGITS[R.id.btn6] = 6
        DIGITS[R.id.btn7] = 7
        DIGITS[R.id.btn8] = 8
        DIGITS[R.id.btn9] = 9
    }
}