package verify_pack

import com.devskiller.calculator.Calculator
import com.devskiller.calculator.Operation
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
import org.junit.Test
import java.math.BigInteger

class CalculatorTest {
    private var calculator: Calculator = Calculator()

    @Test
    fun shouldSetValueOnEmptyCalculator() {
        //when
        val result = calculator.evaluate(BigInteger.TEN)

        //then
        assertThat(result).isEqualTo(BigInteger.TEN)
    }

    @Test
    fun shouldEvaluateAdding() {
        //given
        calculator.evaluate(BigInteger.ONE)
        calculator.operation = Operation.ADD

        //when
        val result = calculator.evaluate(BigInteger.ONE)

        //then
        assertThat(result).isEqualTo(BigInteger("2"))
    }

    @Test
    fun shouldEvaluateSubtraction() {
        //given
        calculator.evaluate(BigInteger.TEN)
        calculator.operation = Operation.SUBTRACT

        //when
        val result = calculator.evaluate(BigInteger.ONE)

        //then
        assertThat(result).isEqualTo(BigInteger("9"))
    }

    @Test
    fun shouldEvaluateMultiplication() {
        //given
        calculator.evaluate(BigInteger.TEN)
        calculator.operation = Operation.MULTIPLY

        //when
        val result = calculator.evaluate(BigInteger.TEN)

        //then
        assertThat(result).isEqualTo(BigInteger("100"))
    }

    @Test
    fun shouldEvaluateDivision() {
        //given
        calculator.evaluate(BigInteger.TEN)
        calculator.operation = Operation.DIVIDE

        //when
        val result = calculator.evaluate(BigInteger.valueOf(5))

        //then
        assertThat(result).isEqualTo(BigInteger("2"))
    }

    @Test(expected = ArithmeticException::class)
    fun shouldThrowWhenDividedByZero() {
        //given
        calculator.evaluate(BigInteger.TEN)
        calculator.operation = Operation.DIVIDE

        //when
        calculator.evaluate(BigInteger.ZERO)

        //then
        fail("Exception wasn't thrown! Should ever get here!")
    }
}