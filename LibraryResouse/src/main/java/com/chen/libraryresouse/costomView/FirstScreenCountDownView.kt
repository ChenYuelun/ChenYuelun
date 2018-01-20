package com.chen.libraryresouse.costomView

import android.content.Context
import android.os.CountDownTimer
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.chen.libraryresouse.R
import com.chen.libraryresouse.utils.log

/**
 * Created by chenyuelun on 2018/1/20.
 */
class FirstScreenCountDownView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {
    private var mCountDownTimer: CountDownTimer? = null
    private var countDownClickListener: CountDownClickListener? = null
    private var mTvTime: TextView? = null
    private var fl_countDown: FrameLayout? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.view_first_screen_count_down, this)
    }

    /**
     * Created by yyz on 02/06/2017.
     * 重写点击事件
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (event.action) {
            MotionEvent.ACTION_UP -> if (countDownClickListener != null) {
                countDownClickListener!!.click()
            }
        }
        //拦截点击事件不再继续传递
        return true
    }
    /**
     * Created by yyz on 01/06/2017.
     *
     * @param countTime 倒计时的时长
     */
    fun setCountDownTimer(countTime: Int) {

        log("welcomeActivity", "counttime" + countTime)
        mTvTime!!.text = "跳过" + countTime / 1000

        //两个参数，前一个指倒计时的总时间，后一个指多长时间倒数一下。
        //倒计时结束后调用
        mCountDownTimer = object : CountDownTimer(countTime.toLong(), 1000) {
            //两个参数，前一个指倒计时的总时间，后一个指多长时间倒数一下。
            override fun onTick(millisUntilFinished: Long) {
                log("welcomeActivity", "" + millisUntilFinished + ";" + millisUntilFinished / 1000)
                val l = millisUntilFinished / 1000
                mTvTime!!.text = "跳过 " + l + "s"
            }

            override fun onFinish() {
                //倒计时结束后调用
                countDownClickListener!!.click()
            }
        }
    }

    /**
     * Created by yyz on 01/06/2017.
     * 开启倒计时
     */
    fun startCount() {
        if (mCountDownTimer != null) {
            log("welcomeActivity", "startCount")
            this@FirstScreenCountDownView.visibility = View.VISIBLE
            mCountDownTimer!!.start()
        }
    }

    /**
     * Created by yyz on 01/06/2017.
     * 关闭倒计时
     */
    fun stopCount() {
        if (mCountDownTimer != null) {
            mCountDownTimer!!.cancel()
        }
    }

    /**
     * Created by yyz on 01/06/2017.
     * 倒计时结束接口
     */
    interface CountDownClickListener {
        fun click()
    }

    fun setCountDownClickListener(countDownClickListener: CountDownClickListener) {
        this.countDownClickListener = countDownClickListener
    }


    fun setViewVisibility(visibility: Int) {
        fl_countDown!!.visibility = visibility
    }
}