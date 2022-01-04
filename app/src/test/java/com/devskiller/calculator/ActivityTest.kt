package com.devskiller.calculator

import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import com.devskiller.calculator.calculator.R
import org.assertj.android.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.N])
class ActivityTest {

    private lateinit var activity: MainActivity
    private lateinit var result: TextView

    @Before
    fun setUp() {
        activity = Robolectric.buildActivity(MainActivity::class.java).setup().get()
        result = activity.findViewById(R.id.result)
    }

    @Test
    fun shouldClearLatestDigitWhenBackIsPressed() {
        // given
        click(R.id.btn1)
        click(R.id.btn2)
        click(R.id.btn3)

        // when
        click(R.id.backspace)

        // then
        assertThat(result).hasText("12")
    }

    @Test
    fun shouldAdd() {
        // given
        click(R.id.btn2)
        click(R.id.btnAdd)
        click(R.id.btn2)

        // when
        click(R.id.btnEqual)

        // then
        assertThat(result).hasText("4")
    }

    @Test
    fun shouldMultiply() {
        // given
        click(R.id.btn3)
        click(R.id.btnMultiply)
        click(R.id.btn2)

        // when
        click(R.id.btnEqual)

        // then
        assertThat(result).hasText("6")
    }

    @Test
    fun shouldReportErrorWhenDivideByZero() {
        // given
        click(R.id.btn8)
        click(R.id.btnDivide)
        click(R.id.btn0)

        // when
        click(R.id.btnEqual)

        // then
        assertThat(result).hasText("E")
    }

    @Test
    fun shouldClearScreenWhenCEIsPressed() {
        // given
        click(R.id.btn1)
        click(R.id.btn2)

        // when
        click(R.id.ce)

        // then
        assertThat(result).hasText("0")
    }

    private fun click(@IdRes id: Int) {
        activity.findViewById<View>(id).performClick()
    }
}