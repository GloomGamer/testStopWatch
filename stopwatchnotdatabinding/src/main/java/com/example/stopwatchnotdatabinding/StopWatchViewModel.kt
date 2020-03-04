package com.example.stopwatchnotdatabinding

import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.concurrent.timer

class StopWatchViewModel : ViewModel(){

    var time: Long = 0
    var running = false
    var timerTask: Timer? = null
    var callback: StopWatchCallback? = null

    init {
        reset()
    }

    fun clickStart() {
        when (running) {
            false -> {
                running = true
                timerTask = timer(period = 10) {
                    time++

                    val min = (time / 100 / 60).toInt()
                    val sec = (time / 100 % 60).toInt()
                    val milSec = (time % 100).toInt()

                    callback?.timeChanged(min, sec, milSec)
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
        callback?.timeChanged(0, 0, 0)
    }

    private fun reset() {
        time = 0
    }
}