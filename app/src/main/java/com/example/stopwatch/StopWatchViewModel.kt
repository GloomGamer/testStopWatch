package com.example.stopwatch

import android.app.Activity
import androidx.databinding.Bindable
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.concurrent.timer

class StopWatchViewModel : ViewModel(){

    val minute = ObservableInt()
    val second = ObservableInt()
    val milliSecond = ObservableInt()

    var time = 0
    var running = false
    var timerTask: Timer? = null

    init {
        reset()
    }

    fun clickStart() {
        when (running) {
            false -> {
                running = true
                timerTask = timer(period = 10) {
                    time++
                    second.set(time / 100 % 60)
                    minute.set(time / 100 / 60)
                    milliSecond.set(time % 100)
                }
            }
        }
    }

    fun clickPause() {
        timerTask?.cancel()
        running = false
    }

    fun clickReset() {
        reset()
    }

    private fun reset() {
        time = 0
        minute.set(0)
        second.set(0)
        milliSecond.set(0)
    }
}