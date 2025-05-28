package com.vivo.familycare.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.android.internal.graphics.drawable.BackgroundBlurDrawable
import com.vivo.familycare.R

/**
 *
 * Description: 描述类的作用
 * Author: lvjiajingosc
 * CreateDate: 2025/5/28 17:31
 *
 */
class BatteryView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var batteryLevel = 100 // 初始电池电量
    private val fillPaint = Paint().apply {
        style = Paint.Style.FILL
    }
    private var backgroundDrawable = ContextCompat.getDrawable(context, R.drawable.child_status_dark_blue_battery_full)
    private val cornerRadius = 6f // 圆角大小
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = 128
        val desiredHeight = 88
        // 计算最终宽高
        val width = resolveSize(desiredWidth, widthMeasureSpec)
        val height = resolveSize(desiredHeight, heightMeasureSpec)
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = measuredWidth.toFloat()
        val height = measuredHeight.toFloat()

        // 绘制电池背景
        backgroundDrawable?.setBounds(0, 0, width.toInt(), height.toInt())
        backgroundDrawable?.draw(canvas)

        // 计算填充高度
        val fillWidth = width * (batteryLevel / 100f)

        // 颜色渐变
        fillPaint.color = when {
            batteryLevel > 80 -> Color.GREEN
            batteryLevel > 50 -> Color.YELLOW
            else -> Color.GRAY
        }

        // 创建圆角路径
        val path = Path().apply {
            addRoundRect(RectF(0f, 0f, fillWidth, height), floatArrayOf(
                cornerRadius, cornerRadius, // top-left
                0f, 0f,                     // top-right
                0f, 0f,                     // bottom-right
                cornerRadius, cornerRadius // bottom-left
            ), Path.Direction.CW)
        }

        // 裁剪填充区域
        canvas.clipPath(path)
        // 绘制电量填充
        canvas.drawRect(0f, 0f, fillWidth, height , fillPaint)
    }

    fun setBatteryLevel(level: Int) {
        batteryLevel = level
        postInvalidate()
    }

    fun setBackgroundDrawables(backgroundDrawable: Drawable){
        this.backgroundDrawable =backgroundDrawable;
        postInvalidate()
    }
}
