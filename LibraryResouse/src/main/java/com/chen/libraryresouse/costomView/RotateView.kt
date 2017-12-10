package com.chen.libraryresouse.costomView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.LinearLayout
import com.chen.libraryresouse.R
import kotlinx.android.synthetic.main.loading_circle_view.view.*

/**
 * Created by ${ChenYuelun} on 2017/12/10.
 *
 *----------Dragon be here!----------/
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑
 * 　　　　┃　　　┃代码无BUG！
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━━━━━━
 *
 *说明：
 */
class RotateView(context: Context) : LinearLayout(context) {

    constructor(context: Context, attrs: AttributeSet) : this(context) {
        val pivotValue = 0.5f    // SUPPRESS CHECKSTYLE
        val toDegree = 720.0f    // SUPPRESS CHECKSTYLE
        val mRotateAnimation = RotateAnimation(0.0f, toDegree, Animation.RELATIVE_TO_SELF, pivotValue,
                Animation.RELATIVE_TO_SELF, pivotValue)
        mRotateAnimation.fillAfter = true;
        mRotateAnimation.interpolator = LinearInterpolator()
        mRotateAnimation.duration = 1200
        mRotateAnimation.repeatCount = Animation.INFINITE
        mRotateAnimation.repeatMode = Animation.RESTART
        val fl = LayoutInflater.from(context).inflate(R.layout.loading_circle_view, null)
        fl.loadingcircleview.animation = mRotateAnimation
        this.addView(fl)
    }

}