package com.guillem.simonsays
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class SimonButtonView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val colors = listOf(Color.GREEN, Color.BLUE, Color.YELLOW, Color.RED)
    private val paint = Paint().apply {
        style = Paint.Style.FILL
        isAntiAlias = true
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = Color.GREEN
        canvas.drawCircle(800f, 700f, 200f, paint)
        paint.color = Color.RED
        canvas.drawCircle(300f, 1200f, 200f, paint)
        paint.color = Color.BLUE
        canvas.drawCircle(300f, 700f, 200f, paint)
        paint.color = Color.YELLOW
        canvas.drawCircle(800f, 1200f, 200f, paint)
    }

    interface CircleClickListener {
        fun onCircleClicked(circleColor: Int)
    }

    class CircleClickListenerImpl : CircleClickListener {
        override fun onCircleClicked(circleColor: Int) {
            when (circleColor) {
                Color.GREEN -> println("Green circle clicked")
                Color.RED -> println("Red circle clicked")
                Color.BLUE -> println("Blue circle clicked")
                Color.YELLOW -> println("Yellow circle clicked")
                else -> println("Unknown circle clicked")
            }
        }
    }
}