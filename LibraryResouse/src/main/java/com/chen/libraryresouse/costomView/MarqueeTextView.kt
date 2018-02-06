package com.chen.libraryresouse.costomView

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.animation.LinearInterpolator
import android.view.animation.TranslateAnimation
import android.widget.TextSwitcher
import android.widget.TextView
import android.widget.ViewSwitcher
import com.chen.libraryresouse.R
import com.chen.libraryresouse.utils.DensityUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import java.util.concurrent.TimeUnit
import kotlin.reflect.jvm.internal.impl.protobuf.Internal


/**
 * Created by chenyuelun on 2018/2/6.
 */
class MarqueeTextView : TextSwitcher, ViewSwitcher.ViewFactory {

    private lateinit var content: List<String>
    private var observable: Disposable? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init()
    }

    fun init() {
        setFactory(this)
        setAnimation()
    }

    override fun makeView(): View {
        val textView = TextView(context)
        //设置垂直居中
        textView.gravity = Gravity.CENTER_VERTICAL
        //设置字号
        textView.textSize = 13f
        //设置显示行数
        textView.maxLines = 1
        //设置内边距
        textView.setPadding(0, DensityUtils.dip2px(context, 11f).toInt(), 0, DensityUtils.dip2px(context, 12f).toInt())
        //设置文字颜色
        textView.setTextColor(resources.getColor(R.color.text333))
        //末尾省略
        textView.ellipsize = TextUtils.TruncateAt.valueOf("END")
        return textView
    }

    fun setAnimation() {
        setInAnimation(context, R.anim.marquee_bottom_in)
        setOutAnimation(context, R.anim.marquee_top_out)
    }

    fun setContent(content: List<String>) {
        this.content = content
        startPlay()
    }

    var count = 0
    var position = 0
    fun startPlay() {
        if (content.isNotEmpty() && observable == null) {
            observable = Observable.interval(0, 5, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        position = count % content.size
                        setText(content[position])
                        count++
                    })
        }
    }

    fun stopPlay() {
        if (observable != null) {
            observable!!.dispose()
            observable = null
        }
    }


    fun getVisibleItemPosition() = position
}