package com.chen.libraryresouse.costomView

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.chen.libraryresouse.utils.DensityUtils

/**
 * Created by chenyuelun on 2018/1/22.
 */
class LoadingCircleView : View{
    constructor(context: Context):super(context)

    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet)


    //View默认最小宽度
    private val DEFAULT_MIN_WIDTH = 400
    //圆环颜色
    private val doughnutColors = intArrayOf(Color.parseColor("#afafaf"), Color.parseColor("#00000000"))

    private var width: Float = 0f
    private var height: Float = 0f
    private val paint = Paint()


    private fun resetParams() {
        width = getWidth().toFloat()
        height = getHeight().toFloat()
    }

    private fun initPaint() {
        paint.reset()
        paint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        resetParams()
        //画背景白色圆环
        initPaint()
        val doughnutWidth = DensityUtils.dip2px(context, 2f).toFloat()
        paint.isAntiAlias = true
        val rectF = RectF(0 + doughnutWidth, 0 + doughnutWidth,
                width - doughnutWidth, height - doughnutWidth)
        //画彩色圆环
        initPaint()
        paint.strokeWidth = doughnutWidth
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
        paint.shader = SweepGradient((width / 2).toFloat(), (height / 2).toFloat(), doughnutColors, null)
        canvas.drawArc(rectF, 0f, 360f, false, paint)
    }

    /**
     * 当布局为wrap_content时设置默认长宽
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec))
    }

    private fun measure(origin: Int): Int {
        var result = DEFAULT_MIN_WIDTH
        val specMode = View.MeasureSpec.getMode(origin)
        val specSize = View.MeasureSpec.getSize(origin)
        if (specMode == View.MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            if (specMode == View.MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize)
            }
        }
        return result
    }


}