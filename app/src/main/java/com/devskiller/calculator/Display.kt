package com.devskiller.calculator

import android.os.Bundle
import java.math.BigInteger

class Display {

    private var value: BigInteger = BigInteger.ZERO
    private var completed = false

    fun insertDigit(digit: Int) {
        if (completed) {
            value = BigInteger.ZERO
            completed = false
        }
        value = value.multiply(BigInteger.TEN).add(BigInteger.valueOf(digit.toLong()))
    }

    fun removeDigit() {
        value = value.divide(BigInteger.TEN)
    }

    fun clear() {
        value = BigInteger.ZERO
    }

    fun getValue(): BigInteger {
        return value
    }

    fun setValue(value: BigInteger) {
        this.value = value
        completed = true
    }

    val line: String
        get() = value.toString()
}