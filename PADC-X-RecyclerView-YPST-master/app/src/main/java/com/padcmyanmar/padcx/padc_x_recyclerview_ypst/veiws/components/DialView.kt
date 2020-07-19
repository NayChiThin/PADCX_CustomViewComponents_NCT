package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.veiws.components

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.R
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

private enum class FanSpeed(val label:Int) {
    OFF(R.string.fan_off),
    LOW(R.string.fan_low),
    MEDIUM(R.string.fan_medium),
    HIGH(R.string.fan_high);
}
private const val RADIUS_OFFSET_LABEL = 30
private const val RADIUS_OFFSET_INDICATOR = -35

private var radius = 0.0f
private var fanSpeed = FanSpeed.OFF

private val pointPosition:PointF = PointF(0.0f,0.0f)

class DialView @JvmOverloads constructor(
    context: Context,
    attrs:AttributeSet,
    defStyleAttr:Int =0
):View(context,attrs,defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 55.0f
        typeface = Typeface.create("",Typeface.BOLD)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = (min(w,h)/2.0 * 0.8).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawBackground(canvas)
        drawIndicatorCircle(canvas)
        drawTextLabels(canvas)
    }
    private fun PointF.computeXYForSpeed(pos:FanSpeed,radius:Float) {
        val startAngle = Math.PI*(9/8.0)
        val angle = startAngle+pos.ordinal*(Math.PI/4)
        x = (radius* cos(angle)).toFloat() + width/2
        y = (radius * sin(angle)).toFloat() + height/2
    }
    private fun drawBackground(canvas: Canvas) {
        paint.color = if(fanSpeed==FanSpeed.OFF) Color.GRAY else Color.GREEN

        canvas.drawCircle((width/2).toFloat(),(height/2).toFloat(), radius,paint)
    }
    private fun drawIndicatorCircle(canvas: Canvas) {
        val markerRadius = radius + RADIUS_OFFSET_INDICATOR

        pointPosition.computeXYForSpeed(fanSpeed,markerRadius)

        paint.color = Color.BLACK
        canvas.drawCircle(pointPosition.x, pointPosition.y, radius/12,paint)
    }
    private fun drawTextLabels(canvas: Canvas) {
        val labelRadius = radius + RADIUS_OFFSET_LABEL

        for(i in FanSpeed.values()) {
            pointPosition.computeXYForSpeed(i,labelRadius)
            val label = resources.getString(i.label)
            canvas.drawText(label, pointPosition.x, pointPosition.y,paint)
        }
    }
}