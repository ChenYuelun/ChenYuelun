package com.chen.chenyuelun.data.network

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.text.TextUtils
import android.widget.Toast
import com.chen.librarynetwork.exception.ApiException
import com.chen.librarynetwork.subscribers.BaseSubscriber
import com.chen.libraryresouse.utils.LogUtils
import com.chen.libraryresouse.utils.PhoneParameterUtils.Companion.isNetworkAvailable
import com.chen.libraryresouse.utils.toast
import org.reactivestreams.Subscription

/**
 * Created by chenyuelun on 2018/1/20.
 */
open class RxSubscriber<T> : BaseSubscriber<T>{

    //正常返回code值 = 0
    private val NORMAL_CODE = 0
    //是否显示 进度条
    private var isShowProgress = false
    private val mContext: Context


    constructor (context: Context, isShowProgress: Boolean) : super() {
        this.mContext = context
        this.isShowProgress = isShowProgress
    }

    constructor(context: Context) : super() {
        this.mContext = context
    }

    override fun onSubscribe(s: Subscription?) {
        if (isNetworkAvailable(mContext)) {
            if (isShowProgress) {
//                DialogHelper.showProgressDlg(mContext)
                toast("正在加载。。。")
            }
        }
    }

    override fun onError(ex: ApiException) {
        LogUtils.d("RxSubscriber", "onError: " + ex.message + "code: " + ex.code)
        val message = ex.message;
        if (message != null && message.isNotEmpty()) {
            toast(message)
        }
    }

    override fun onComplete() {
        toast("数据加载完成")
    }
    override fun onNext(t: T) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}