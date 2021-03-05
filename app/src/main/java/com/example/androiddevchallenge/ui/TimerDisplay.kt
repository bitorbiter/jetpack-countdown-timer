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

import android.graphics.Paint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp

@Composable
fun TimerDisplay(remainingMillis: Long, setMillis: Long) {
    val colors = MaterialTheme.colors
    val seconds = remainingMillis / 1000 % 60
    val progress = 360 * remainingMillis / setMillis

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(280.dp)
            .aspectRatio(1f)

            .drawBehind {
                rotate(-90f) {
                    drawCircle(Color.White, size.minDimension / 2.0f, center)
                    drawArc(
                        colors.primary,
                        seconds * (360f / 60) - 1.5f,
                        3f,
                        useCenter = true,
                        size = this.size.copy(
                            width = size.minDimension,
                            height = size.minDimension
                        ),
                        topLeft = Offset((size.width - size.minDimension) / 2, 0f)
                    )
                    for (i in 1..120) {
                        val offsetLength = 20f
                        drawArc(
                            Color.Black,
                            (i * (360 / 120)) - 0.25f,
                            0.5f,
                            useCenter = true,
                            size = this.size.copy(
                                width = size.minDimension - offsetLength,
                                height = size.minDimension - offsetLength
                            ),
                            topLeft = Offset(
                                (size.width - size.minDimension + offsetLength) / 2,
                                offsetLength / 2
                            )
                        )
                    }
                    for (i in 1..12) {
                        val offsetLength = 20f
                        drawArc(
                            Color.Black,
                            (i * (360 / 12)) - 0.5f,
                            1f,
                            useCenter = true,
                            size = this.size.copy(
                                width = size.minDimension - offsetLength,
                                height = size.minDimension - offsetLength
                            ),
                            topLeft = Offset(
                                (size.width - size.minDimension + offsetLength) / 2,
                                offsetLength / 2
                            )
                        )
                    }
                    drawCircle(colors.background, size.minDimension / 2.0f * 0.85f, center)
                    val offsetLength = 130f
                    drawArc(
                        colors.primary,
                        0f,
                        progress.toFloat(),
                        useCenter = true,
                        size = this.size.copy(
                            width = size.minDimension - offsetLength,
                            height = size.minDimension - offsetLength
                        ),
                        topLeft = Offset(
                            (size.width - size.minDimension + offsetLength) / 2,
                            offsetLength / 2
                        )
                    )
                    drawCircle(colors.background, size.minDimension / 2.0f * 0.8f, center)
                }

                val textPaint = Paint()
                textPaint.textAlign = Paint.Align.CENTER
                textPaint.textSize = 180f
                textPaint.color = Color.Black.toArgb()
            }
    ) {
        TimerText(
            secondsMillis = remainingMillis
        )
    }
}

@Composable
fun TimerText(secondsMillis: Long) {
    Text(
        text = format(secondsMillis),
        style = MaterialTheme.typography.h2
    )
}

private fun format(remainingTimeInSeconds: Long): String {
    val minutes = (remainingTimeInSeconds / 1000 / 60)
    val seconds = (remainingTimeInSeconds / 1000 - (minutes * 60))
    return "${pad(minutes)}:${pad(seconds)}"
}

private fun pad(seconds: Long): String {
    return seconds.toString().padStart(2, '0')
}
