package com.example.stopwatchnotdatabinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(StopWatchViewModel::class.java)

        btnStart.setOnClickListener {
            viewModel.clickStart()
        }
        btnPause.setOnClickListener {
            viewModel.clickPause()
        }
        btnReset.setOnClickListener {
            viewModel.clickReset()
        }

        viewModel.callback = object:StopWatchCallback {
            override fun timeChanged(min: Int, sec: Int, milSec: Int) {
                runOnUiThread {
                    minute.text = min.toString()
                    second.text = sec.toString()
                    millisecond.text = milSec.toString()
                }
            }
        }
    }
}
