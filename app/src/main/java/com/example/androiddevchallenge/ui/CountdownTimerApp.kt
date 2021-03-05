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
package com.example.androiddevchallenge.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.countdowntimer.CountdownTimer
import com.example.androiddevchallenge.ui.theme.CountdownTimerTheme
import com.example.androiddevchallenge.viewmodel.TimerModel

// Start building your app here!
@Composable
fun CountdownTimerApp(timerModel: TimerModel) {
    val remainingSeconds: Long by timerModel.remainingTime.observeAsState(60000)
    val setMillis: Long by timerModel.setMillis.observeAsState(60000)

    Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Countdown", style = MaterialTheme.typography.h4)
            Spacer(modifier = Modifier.height(40.dp))
            TimerDisplay(remainingSeconds, setMillis)
            Spacer(modifier = Modifier.height(150.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                val running: Boolean by timerModel.running.observeAsState(false)
                IconButton(onClick = { timerModel.reset() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.restart_alt_24px),
                        contentDescription = "reset countdown"
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                IconButton(onClick = { timerModel.startOrPause() }) {
                    if (running) {
                        Icon(
                            painter = painterResource(id = R.drawable.pause_circle_24px),
                            contentDescription = "pause"
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.not_started_24px),
                            contentDescription = "increment time"
                        )
                    }
                }
                Spacer(modifier = Modifier.width(20.dp))
                IconButton(onClick = { timerModel.incrementTime() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.add_circle_outline_24px),
                        contentDescription = "increment time"
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                IconButton(onClick = { timerModel.decrementTime() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.remove_circle_outline_24px),
                        contentDescription = "decrement time"
                    )
                }
            }
        }
    }
}

@Preview("Light Theme")
@Composable
fun LightPreview() {
    CountdownTimerTheme {
        CountdownTimerApp(TimerModel(CountdownTimer()))
    }
}

@Preview("Dark Theme")
@Composable
fun DarkPreview() {
    CountdownTimerTheme(darkTheme = true) {
        CountdownTimerApp(TimerModel(CountdownTimer()))
    }
}
