package com.devskiller.calculator

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import com.devskiller.calculator.calculator.R
import org.assertj.android.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.N])
class ActivityLifecycleTest {

    private lateinit var controller: ActivityController<MainActivity>
    private lateinit var activity: MainActivity
    private lateinit var result: TextView

    @Before
    fun setUp() {
        controller = Robolectric.buildActivity(MainActivity::class.java)
        controller.setup().get().also {
            activity = it
            result = activity.findViewById(R.id.result)
        }
    }

    @After
    fun tearDown() {
        controller.pause()
            .stop()
            .destroy()
    }

    @Test
    fun shouldKeepCalculatorValuesAfterActivityPauseAndResume() {
        // given
        click(R.id.btn1)
        click(R.id.btn2)
        click(R.id.btn3)

        // when
        controller.pause().resume()

        // then
        assertThat(result).hasText("123")
    }

    @Test
    fun shouldKeepCalculatorValuesAfterActivityRecreation() {
        // given
        click(R.id.btn1)
        click(R.id.btn2)
        click(R.id.btn3)
        val bundle = Bundle()

        // when
        destroyOriginalActivity(bundle)
        bringUpOriginalActivity(bundle)

        // then
        assertThat(result).hasText("123")
    }

    private fun bringUpOriginalActivity(bundle: Bundle) {
        controller = Robolectric.buildActivity(MainActivity::class.java)
            .setup(bundle)
        activity = controller.get()
        result = activity.findViewById(R.id.result)
    }

    private fun destroyOriginalActivity(bundle: Bundle) {
        controller.saveInstanceState(bundle)
            .pause()
            .stop()
            .destroy()
    }

    private fun click(@IdRes id: Int) {
        activity.findViewById<View>(id).performClick()
    }
}