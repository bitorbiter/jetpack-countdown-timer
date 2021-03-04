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
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.viewmodel.TimerModel

@Composable
fun TimerDisplay(timerModel: TimerModel) {
    val colors = MaterialTheme.colors
    val remainingSeconds: String by timerModel.remainingTime.observeAsState("")
    val seconds: Int by timerModel.seconds.observeAsState(0)
    val progress: Int by timerModel.progress.observeAsState(0)

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp),
        onDraw = {

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

            drawIntoCanvas {
                it.nativeCanvas.drawText(
                    remainingSeconds,
                    center.x,
                    center.y + 60,
                    textPaint
                )
            }
        }
    )
}
