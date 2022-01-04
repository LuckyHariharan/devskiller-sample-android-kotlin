package com.devskiller.calculator

import android.os.Build
import androidx.annotation.RequiresApi
import java.math.BigInteger
import java.util.function.BinaryOperator

enum class Operation(private val operationHandler: BinaryOperator<BigInteger>) {
    NONE(BinaryOperator<BigInteger> { _, arg -> arg }),
    ADD(BinaryOperator(BigInteger::add)),
    SUBTRACT(BinaryOperator(BigInteger::subtract)),
    MULTIPLY(BinaryOperator(BigInteger::multiply)),
    DIVIDE(BinaryOperator(BigInteger::divide));

    @RequiresApi(Build.VERSION_CODES.N)
    fun perform(acc: BigInteger, argument: BigInteger): BigInteger {
        return operationHandler.apply(acc, argument)
    }
}