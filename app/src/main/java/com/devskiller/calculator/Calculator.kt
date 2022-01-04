package com.devskiller.calculator

import java.math.BigInteger

class Calculator(var operation: Operation = Operation.NONE) {

    private var accumulator = BigInteger.ZERO

    fun evaluate(value: BigInteger): BigInteger {
        return operation.perform(accumulator, value).also { accumulator = it }
    }

    fun clear() {
        operation = Operation.NONE
        accumulator = BigInteger.ZERO
    }
}