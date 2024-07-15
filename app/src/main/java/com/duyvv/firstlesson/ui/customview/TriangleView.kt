package com.duyvv.firstlesson.ui.customview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.duyvv.firstlesson.domain.Point

class TriangleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 10f
    }

    private val path = Path()

    // Các điểm của tam giác
    private var point1 = Point(0f, 0f)
    private var point2 = Point(0f, 0f)
    private var point3 = Point(0f, 0f)

    fun setPoints(p1: Point, p2: Point, p3: Point) {
        point1 = p1
        point2 = p2
        point3 = p3
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        path.reset() // reset path before draw
        path.moveTo(point1.x, point1.y) // start draw from point1
        path.lineTo(point2.x, point2.y) // draw line from point1 to point2
        path.lineTo(point3.x, point3.y) // draw line from point2 to point3
        path.close()
        canvas.drawPath(path, paint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null && (event.action == MotionEvent.ACTION_DOWN)) {
            val x = event.x
            val y = event.y
            if (isPointInTriangle(x, y)) {
                Toast.makeText(context, "Point is inside the triangle", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(context, "Point is outside the triangle", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        return true
    }

    private fun isPointInTriangle(px: Float, py: Float): Boolean {
        val p = Point(px, py)

        val b1 = sign(p, point1, point2) < 0.0f
        val b2 = sign(p, point2, point3) < 0.0f
        val b3 = sign(p, point3, point1) < 0.0f

        return (b1 == b2) && (b2 == b3)
    }

    private fun sign(p: Point, p1: Point, p2: Point): Float {
        return (p.x - p2.x) * (p1.y - p2.y) - (p1.x - p2.x) * (p.y - p2.y)
    }
}
