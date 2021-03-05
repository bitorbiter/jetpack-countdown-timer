/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.countdowntimer

import android.os.CountDownTimer
import kotlinx.coroutines.flow.MutableStateFlow

class CountdownTimer {
    var setMillis: MutableStateFlow<Long> = MutableStateFlow(60000)
    val remainingSeconds: MutableStateFlow<Long> = MutableStateFlow(60000)

    private var countDownTimer: CountDownTimer? = null

    fun reset() {
        remainingSeconds.value = setMillis.value
    }

    val running = MutableStateFlow(false)

    fun startStop() {
        if (countDownTimer == null) {
            countDownTimer =
                object : CountDownTimer(remainingSeconds.value, 100) {
                    override fun onTick(millisUntilFinished: Long) {
                        remainingSeconds.value = millisUntilFinished
                    }

                    override fun onFinish() {
                        running.value = false
                    }
                }.start()
            running.value = true
        } else {
            countDownTimer?.cancel()
            countDownTimer = null
            running.value = false
        }
    }

    fun incrementByMinute() {
        setMillis.value += 6000
        remainingSeconds.value = remainingSeconds.value + 6000
    }

    fun decrementByMinute() {
        setMillis.value -= 6000
        remainingSeconds.value = remainingSeconds.value - 6000
    }
}
