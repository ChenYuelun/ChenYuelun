package com.chen.libraryresouse.costomView

import android.content.Context
import android.os.Handler
import android.support.v4.view.MotionEventCompat
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.animation.Interpolator
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

/**
 * Created by chenyuelun on 2018/2/6.
 */
class AutoScrollViewPager : ViewPager {
    /**
     * auto scroll time in milliseconds, default is [.DEFAULT_INTERVAL]
     */
    private var interval = DEFAULT_INTERVAL.toLong()
    /**
     * auto scroll direction, default is [.RIGHT]
     */
    private var direction = RIGHT
    /**
     * whether automatic cycle when auto scroll reaching the last or first item, default is true
     */
    private var isCycle = true
    /**
     * whether stop auto scroll when touching, default is true
     */
    private var stopScrollWhenTouch = true
    /**
     * how to process when sliding at the last or first item, default is [.SLIDE_BORDER_MODE_NONE]
     */
    private var slideBorderMode = SLIDE_BORDER_MODE_NONE
    /**
     * whether animating when auto scroll at the last or first item
     */
    private var isBorderAnimation = true
    /**
     * scroll factor for auto scroll animation, default is 1.0
     */
    private var autoScrollFactor = 1.0
    /**
     * scroll factor for swipe scroll animation, default is 1.0
     */
    private var swipeScrollFactor = 1.0

    private var isAutoScroll = false
    private var isStopByTouch = false
    private var touchX = 0f
    private var downX = 0f
    private var scroller: CustomDurationScroller? = null

    // 滑动距离及坐标
    private var xDistance: Float = 0f
    private var yDistance: Float = 0f
    private var xLast: Float = 0f
    private var yLast: Float = 0f

    private var isCanScroll = true
    private var observable: Disposable? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init()
    }

    private fun init() {
        setViewPagerScroller()
    }

    private fun setViewPagerScroller() {
        try {
            val scrollerField = ViewPager::class.java.getDeclaredField("mScroller")
            scrollerField.isAccessible = true
            val interpolatorField = ViewPager::class.java.getDeclaredField("sInterpolator")
            interpolatorField.isAccessible = true

            scroller = CustomDurationScroller(context, interpolatorField.get(null) as Interpolator)
            scrollerField.set(this, scroller)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun startAutoScroll() {
        isAutoScroll = true
        delayPlay((interval + scroller!!.getDuration() / autoScrollFactor * swipeScrollFactor).toLong())
    }

    fun stopAutoScroll() {
        isAutoScroll = false
        if (observable !=null){
            observable!!.dispose()
            observable = null
        }
    }


    fun startAutoScroll(delayTimeInMills: Long) {
        isAutoScroll = true
        delayPlay(delayTimeInMills)
    }

    private fun delayPlay(delayTimeInMills: Long) {
        if (observable == null){
            observable = Observable.interval(0, delayTimeInMills, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        scroller!!.scrollFactor = autoScrollFactor
                        scrollOnce()
                        scroller!!.scrollFactor = swipeScrollFactor
                    })
        }
    }

    /**
     * scroll only once
     */
    fun scrollOnce() {
        var currentItem = currentItem
        var totalCount = 0
        if (adapter != null){totalCount = adapter!!.count}
        if (adapter == null || totalCount <= 1) {
            return
        }

        val nextItem = if (direction == LEFT) --currentItem else ++currentItem
        if (nextItem < 0) {
            if (isCycle) {
                setCurrentItem(totalCount - 1, isBorderAnimation)
            }
        } else if (nextItem == totalCount) {
            if (isCycle) {
                setCurrentItem(0, isBorderAnimation)
            }
        } else {
            setCurrentItem(nextItem, true)
        }
    }


    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {

        val action = MotionEventCompat.getActionMasked(ev)

        if (stopScrollWhenTouch) {
            if (action == MotionEvent.ACTION_DOWN && isAutoScroll) {
                isStopByTouch = true
                stopAutoScroll()
            } else if (ev.action == MotionEvent.ACTION_UP && isStopByTouch) {
                startAutoScroll()
            }
        }

        if (slideBorderMode == SLIDE_BORDER_MODE_TO_PARENT || slideBorderMode == SLIDE_BORDER_MODE_CYCLE) {
            touchX = ev.x
            if (ev.action == MotionEvent.ACTION_DOWN) {
                downX = touchX
            }
            val currentItem = currentItem
            val adapter = adapter
            val pageCount = adapter?.count ?: 0
            /**
             * current index is first one and slide to right or current index is last one and slide to left.<br></br>
             * if slide border mode is to parent, then requestDisallowInterceptTouchEvent false.<br></br>
             * else scroll to last one when current item is first one, scroll to first one when current item is last
             * one.
             */
            if (currentItem == 0 && downX <= touchX || currentItem == pageCount - 1 && downX >= touchX) {
                if (slideBorderMode == SLIDE_BORDER_MODE_TO_PARENT) {
                    parent.requestDisallowInterceptTouchEvent(false)
                } else {
                    if (pageCount > 1) {
                        setCurrentItem(pageCount - currentItem - 1, isBorderAnimation)
                    }
                    parent.requestDisallowInterceptTouchEvent(false)
                }
                return super.dispatchTouchEvent(ev)
            }
        }
        //        getParent().requestDisallowInterceptTouchEvent(true);
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                yDistance = 0f
                xDistance = yDistance
                xLast = ev.x
                yLast = ev.y
            }
            MotionEvent.ACTION_MOVE -> {
                val curX = ev.x
                val curY = ev.y

                xDistance += Math.abs(curX - xLast)
                yDistance += Math.abs(curY - yLast)
                xLast = curX
                yLast = curY

                if (xDistance > yDistance) {
                    parent.requestDisallowInterceptTouchEvent(true)
                } else {
                    parent.requestDisallowInterceptTouchEvent(false)
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun scrollTo(x: Int, y: Int) {
        if (isCanScroll) {
            super.scrollTo(x, y)
        }
    }


    companion object {
        val DEFAULT_INTERVAL = 1500

        val LEFT = 0
        val RIGHT = 1

        /**
         * do nothing when sliding at the last or first item
         */
        val SLIDE_BORDER_MODE_NONE = 0
        /**
         * cycle when sliding at the last or first item
         */
        val SLIDE_BORDER_MODE_CYCLE = 1
        /**
         * deliver event to parent when sliding at the last or first item
         */
        val SLIDE_BORDER_MODE_TO_PARENT = 2

        val SCROLL_WHAT = 0
    }
}