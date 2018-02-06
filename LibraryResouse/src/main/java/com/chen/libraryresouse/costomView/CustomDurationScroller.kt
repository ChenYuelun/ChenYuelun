package com.chen.libraryresouse.costomView

import android.content.Context
import android.util.AttributeSet
import android.view.animation.Interpolator
import android.widget.Scroller

/**
 * Created by chenyuelun on 2018/2/6.
 */
class CustomDurationScroller : Scroller {
     var scrollFactor = 1.0
    constructor(context: Context) : super(context)
    constructor(context: Context, interpolator: Interpolator) : super(context, interpolator)

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
        super.startScroll(startX, startY, dx, dy, (duration * scrollFactor).toInt())
    }
}