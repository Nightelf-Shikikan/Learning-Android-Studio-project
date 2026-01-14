package com.example.myapplication2.ui.customload

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

import com.example.myapplication2.R


class CircleLoadView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var progress = 0f  // 0..1

    private var circleColor: Int = Color.RED // default color
    private val paint = Paint().apply {
        color = circleColor
        isAntiAlias = true
        strokeWidth = 15f
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
    }
    fun setProgress(value: Float) {
        progress = value.coerceIn(0f, 1f) // keep between 0 and 1
        invalidate() // redraw with new progress
    }

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CircleView,
            0,
            0
        ).apply {
            try {
                paint.color = getColor(
                    R.styleable.CircleView_circleColor,
                    Color.BLUE
                )
            } finally {
                recycle()
            }
        }
    }

    fun setCircleColor(color: Int) {
        circleColor = color
        paint.color = color
        invalidate() // redraw with new color
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val radius = width.coerceAtMost(height) / 2f - paint.strokeWidth
        val cx = width / 2f
        val cy = height / 2f
        val rect = RectF(
            cx - radius,
            cy - radius,
            cx + radius,
            cy + radius
        )

        // Start at top (-90°), sweep by progress*360
        canvas.drawArc(rect, -90f, progress * 360f, false, paint)
    }

}
