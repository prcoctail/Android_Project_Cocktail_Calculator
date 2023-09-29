package ru.partyshaker.partyshaker.utils

import android.os.CountDownTimer
import android.widget.Button
import java.util.concurrent.TimeUnit

class TimerUtil {

private lateinit var timer: CountDownTimer

    fun setTimer(button: Button, content: String, time: Long) {
        button.isClickable = false

        timer = object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) %
                        TimeUnit.HOURS.toMinutes(1)
                val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) %
                        TimeUnit.MINUTES.toSeconds(1)

                button.text =
                    String.format("$content (%02d:%02d)", minutes, seconds)
            }

            override fun onFinish() {
                button.text = content
                button.isClickable = true
            }
        }.start()
    }
}