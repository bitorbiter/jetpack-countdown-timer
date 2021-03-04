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
package com.example.androiddevchallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.example.androiddevchallenge.countdowntimer.CountdownTimer

class TimerModel(private val countdownTimer: CountdownTimer) : ViewModel() {

    val remainingTime: LiveData<String> = countdownTimer.remainingSeconds.asLiveData()
        .map { format(it) }
    val seconds: LiveData<Int> = countdownTimer.remainingSeconds.asLiveData()
        .map { it % 60 }

    private fun format(remainingTimeInSeconds: Int): String {
        val minutes = (remainingTimeInSeconds / 60)
        val seconds = (remainingTimeInSeconds - (minutes * 60))
        return "${pad(minutes)}:${pad(seconds)}"
    }

    private fun pad(seconds: Int): String {
        return seconds.toString().padStart(2, '0')
    }

    fun setSeconds(seconds: Int) {
    }

    val running: LiveData<Boolean> = countdownTimer.running.asLiveData()

    fun reset() {
        countdownTimer.reset()
    }

    fun startOrPause() {
        countdownTimer.startStop()
    }
}
