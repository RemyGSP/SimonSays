package com.guillem.simonsays
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
class SimonSaysView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint()
    private val circleColors = listOf(Color.GREEN, Color.BLUE, Color.YELLOW, Color.RED)
    private var highlightedCircleIndex: Int? = null
    private val circlePositions = listOf(
        Pair(800f, 700f),
        Pair(300f, 1200f),
        Pair(300f, 700f),
        Pair(800f, 1200f)
    )
    private var circleClickListener: CircleClickListener? = null

    init {
        paint.isAntiAlias = true
    }

    fun lightUpCircle(index: Int) {
        highlightedCircleIndex = index
        invalidate() // Request redraw
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for ((i, color) in circleColors.withIndex()) {
            paint.color = color
            canvas.drawCircle(circlePositions[i].first, circlePositions[i].second, 200f, paint)
            if (i == highlightedCircleIndex) {
                drawHighlightedCircle(canvas, circlePositions[i].first, circlePositions[i].second)
            }
        }
    }

    private fun drawHighlightedCircle(canvas: Canvas, x: Float, y: Float) {
        val highlightPaint = Paint().apply {
            color = Color.WHITE
            style = Paint.Style.STROKE
            strokeWidth = 10f
        }
        canvas.drawCircle(x, y, 200f, highlightPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val x = event.x
            val y = event.y
            for ((index, position) in circlePositions.withIndex()) {
                val centerX = position.first
                val centerY = position.second
                if (isInsideCircle(x, y, centerX, centerY, 200f)) {
                    circleClickListener?.onCircleClicked(circleColors[index])
                    break
                }
            }
        }
        return true
    }

    private fun isInsideCircle(x: Float, y: Float, centerX: Float, centerY: Float, radius: Float): Boolean {
        val dx = x - centerX
        val dy = y - centerY
        return (dx * dx + dy * dy) <= radius * radius
    }

    fun setOnCircleClickListener(listener: CircleClickListener) {
        this.circleClickListener = listener
    }

    interface CircleClickListener {
        fun onCircleClicked(color: Int)
    }

    fun clearLitCircle() {
        highlightedCircleIndex = -1
        invalidate()
    }
}