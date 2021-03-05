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

    val remainingTime: LiveData<Long> = countdownTimer.remainingSeconds.asLiveData()
    val setMillis: LiveData<Long> = countdownTimer.setMillis.asLiveData()
    val running: LiveData<Boolean> = countdownTimer.running.asLiveData()

    fun reset() {
        countdownTimer.reset()
    }

    fun startOrPause() {
        countdownTimer.startStop()
    }

    fun incrementTime() {
        countdownTimer.incrementByMinute()
    }

    fun decrementTime() {
        countdownTimer.decrementByMinute()
    }
}
