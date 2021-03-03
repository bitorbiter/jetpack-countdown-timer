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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.CountdownTimerTheme
import com.example.androiddevchallenge.viewmodel.TimerModel

// Start building your app here!
@Composable
fun CountdownTimerApp(timerModel: TimerModel) {
    Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Surface {
                val typography = MaterialTheme.typography
                val remainingSeconds: Int by timerModel.remainingSeconds.observeAsState(0)

                Text(text = "$remainingSeconds", style = typography.h1)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Button(onClick = { timerModel.startOrPause() }) {
                    Text(text = "Start")
                }
                Button(onClick = { timerModel.reset() }) {
                    Text(text = "Reset")
                }
            }
        }
    }
}

@Preview("Light Theme")
@Composable
fun LightPreview() {
    CountdownTimerTheme {
        CountdownTimerApp(TimerModel())
    }
}

@Preview("Dark Theme")
@Composable
fun DarkPreview() {
    CountdownTimerTheme(darkTheme = true) {
        CountdownTimerApp(TimerModel())
    }
}
