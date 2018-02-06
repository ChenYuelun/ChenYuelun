package com.chen.libraryresouse.costomView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.chen.libraryresouse.R
import com.chen.libraryresouse.utils.DensityUtils

/**
 * Created by chenyuelun on 2018/2/6.
 */
class ConfidenceView : View {
    lateinit var mPaint :Paint
    lateinit var mPaint2 :Paint
    //圆环
    private var roundWidth = 0f //宽度
    private var roundColor = 0 //颜色
    //弧
    private var roundProgressColor = 0 //颜色
    //文字
    private var textColor = 0 //颜色
    private var textNumberSize = 0f //数字大小
    private var textPercentSize = 0f //百分号大小
    private var textSize = 0f //文字大小
    private var textNumber: String? = null
    private var probability = 0f
    private var TEXTCONTENT = "预测概率"
    //默认颜色
    private var defaultColor = resources.getColor(R.color.confidence)
    constructor(context: Context) :super(context)

    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet){
        init()
    }

    fun init(){
        mPaint = Paint()
        mPaint2 = Paint()
        roundWidth = DensityUtils.dip2px(context,2f)
        roundColor = resources.getColor(R.color.dc)
        roundProgressColor = defaultColor
        textColor = defaultColor
        textNumberSize = DensityUtils.sp2px(context,21f)
        textPercentSize = DensityUtils.sp2px(context,15f)
        textSize = DensityUtils.sp2px(context,8f)
        probability = 90f
        textNumber = "90"
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //外层大圆
        //得到圆心x坐标
        val center = (width / 2).toFloat()
        //圆的半径
        val radius = center - roundWidth / 2
        mPaint.setColor(roundColor)
        mPaint.setStyle(Paint.Style.STROKE)
        mPaint.setStrokeWidth(roundWidth)
        mPaint.setAntiAlias(true)
        canvas!!.drawCircle(center, center, radius, mPaint)
        //画弧
        mPaint.setColor(roundProgressColor)
        //弧形的边界
        val oval = RectF(center - radius, center - radius, center + radius, center + radius)
        canvas.drawArc(oval, 270f, 360 * (probability / 100), false, mPaint)
        //文字
        mPaint.setStrokeWidth(0f)
        mPaint.setColor(textColor)
        mPaint.setTextSize(textNumberSize)
        val textWidth = mPaint.measureText(textNumber)
        mPaint2.setStrokeWidth(0f)
        mPaint2.setColor(textColor)
        mPaint2.setTextSize(textPercentSize)
        val textWidth2 = mPaint2.measureText("%")
        canvas.drawText(textNumber, center - (textWidth + textWidth2) / 2, height * 0.5f, mPaint)
        canvas.drawText("%", center - (textWidth + textWidth2) / 2 + textWidth, height * 0.5f, mPaint2)
        mPaint.setColor(resources.getColor(R.color.text999))
        mPaint.setTextSize(textSize)
        val textWidth3 = mPaint.measureText(TEXTCONTENT)
        canvas.drawText(TEXTCONTENT, center - textWidth3 / 2, height * 0.75f, mPaint)

    }

    fun setProbability(probability: Float) {
        this.probability = probability
        textNumber = probability.toInt().toString() + ""
        invalidate()
    }

    fun setProbabilityAndName(probability: Float, name: String) {
        this.probability = probability
        textNumber = probability.toInt().toString() + ""
        this.TEXTCONTENT = name
        invalidate()
    }

    fun setDefaultColor(defaultColor: Int) {
        this.defaultColor = defaultColor
        invalidate()
    }

}