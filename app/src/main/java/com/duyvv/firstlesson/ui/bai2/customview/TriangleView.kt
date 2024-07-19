package com.duyvv.firstlesson.ui.bai2.customview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.duyvv.firstlesson.domain.Point
import com.duyvv.firstlesson.ui.bai2.TriangleDialog
import kotlin.math.abs
import kotlin.math.max

class TriangleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }

    private val pointPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        style = Paint.Style.FILL
        strokeWidth = 7f
    }

    private val trianglePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 7f
    }

    // Các điểm của tam giác
    private var p1: Point? = null
    private var p2: Point? = null
    private var p3: Point? = null
    private var touchPoint: Point? = null

    // tỉ lệ vẽ tam giác
    private var scaleFactor: Float = 1f

    fun setPoints(p1: Point, p2: Point, p3: Point) {
        this.p1 = p1
        this.p2 = p2
        this.p3 = p3
        invalidate()
    }

    private fun calculateScaleFactor() {
        if (p1 == null || p2 == null || p3 == null) return
        // tìm toạ độ lớn nhất của 3 điểm
        val maxCoord = max(
            maxOf(abs(p1!!.x), abs(p2!!.x), abs(p3!!.x)),
            maxOf(abs(p1!!.y), abs(p2!!.y), abs(p3!!.y)),
        ) + 0.1f

        // tìm tỷ lệ vẽ sao cho tam giác không bị tràn ra ngoài màn hình
        scaleFactor = minOf(width, height) / (2 * maxCoord)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        calculateScaleFactor()

        val midX = width / 2f
        val midY = height / 2f

        // Vẽ trục Oxy
        drawAxes(canvas, midX, midY)

        if (p1 == null || p2 == null || p3 == null) return
        val screenPoints = listOf(p1!!, p2!!, p3!!).map {
            transformToScreenCoordinates(it, midX, midY)
        }

        // vẽ 3 điểm
        drawPoints(canvas, screenPoints)

        // Vẽ tam giác
        drawTriangle(canvas, screenPoints)

        // vẽ điểm chạm
        touchPoint?.let {
            drawPoints(canvas, listOf(transformToScreenCoordinates(it, midX, midY)))
        }
    }

    private fun drawAxes(canvas: Canvas, midX: Float, midY: Float) {
        canvas.drawLine(0f, midY, width.toFloat(), midY, paint)
        canvas.drawLine(midX, 0f, midX, height.toFloat(), paint)
    }

    private fun drawPoints(canvas: Canvas, points: List<Point>) {
        if (p1 == null || p2 == null || p3 == null) return
        points.forEach {
            canvas.drawCircle(it.x, it.y, 7f, pointPaint)
        }
    }

    private fun drawTriangle(canvas: Canvas, points: List<Point>) {
        canvas.apply {
            drawLine(points[0].x, points[0].y, points[1].x, points[1].y, trianglePaint)
            drawLine(points[1].x, points[1].y, points[2].x, points[2].y, trianglePaint)
            drawLine(points[2].x, points[2].y, points[0].x, points[0].y, trianglePaint)
        }
    }

    // hàm chuyển toạ độ điểm từ Oxy sang toạ độ trên màn hình
    private fun transformToScreenCoordinates(p: Point, midX: Float, midY: Float) =
        Point(midX + p.x * scaleFactor, midY - p.y * scaleFactor)

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (p1 == null || p2 == null || p3 == null) return false

        event?.takeIf { it.action == MotionEvent.ACTION_DOWN }?.let {
            // lấy toạ độ điểm chạm chuyển sang toạ độ Oxy
            touchPoint =
                Point(
                    (it.x - width / 2f) / scaleFactor,
                    (height / 2f - it.y) / scaleFactor,
                )
            invalidate()

            // kiểm tra điểm chạm có nằm trong tam giác hay không
            if (isPointInTriangle(touchPoint!!)) {
                TriangleDialog(context, true).show()
            } else {
                TriangleDialog(context, false).show()
            }
        }
        return true
    }

    // hàm kiểm tra điểm có nằm trong tam giác hay không
    private fun isPointInTriangle(p: Point): Boolean {
        if (p1 == null || p2 == null || p3 == null) {
            return false
        }
        val b1 = sign(p, p1!!, p2!!) < 0.0f
        val b2 = sign(p, p2!!, p3!!) < 0.0f
        val b3 = sign(p, p3!!, p1!!) < 0.0f
        return (b1 == b2) && (b2 == b3)
    }

    private fun sign(p: Point, p1: Point, p2: Point): Float =
        (p.x - p2.x) * (p1.y - p2.y) - (p1.x - p2.x) * (p.y - p2.y)
}