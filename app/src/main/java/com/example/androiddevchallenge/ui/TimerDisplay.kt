package com.example.androiddevchallenge.ui

import android.graphics.Paint
import android.graphics.RectF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.viewmodel.TimerModel

@Composable
fun TimerDisplay(timerModel: TimerModel) {
    val remainingSeconds: String by timerModel.remainingTime.observeAsState("")

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        onDraw = {
            val textPaint = Paint()
            textPaint.textAlign = Paint.Align.CENTER
            textPaint.textSize = 180f
            textPaint.color = 0xffffffff.toInt()

            val circlePaint = Paint()
            circlePaint.color = 0xffbbbbbb.toInt()

            val arcPaint = Paint()
            arcPaint.color = 0xff555555.toInt()

            drawIntoCanvas {
                it.nativeCanvas.drawCircle(center.x, center.y, 300f, circlePaint)
                val mRect = RectF(50f, 50f, 200f, 300f)
                it.nativeCanvas.drawArc(mRect, 0F, 90F, true, arcPaint)
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