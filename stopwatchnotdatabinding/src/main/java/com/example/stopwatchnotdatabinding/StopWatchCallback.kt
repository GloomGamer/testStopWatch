package com.example.stopwatchnotdatabinding

interface StopWatchCallback {
    fun timeChanged(min: Int, sec: Int, milSec: Int)
}