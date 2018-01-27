package com.chen.libraryresouse.costomView

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.chen.libraryresouse.R
import com.chen.libraryresouse.utils.*
import com.chen.libraryresouse.utils.PhoneParameterUtils.Companion.getLayoutParams
import com.chen.libraryresouse.utils.PhoneParameterUtils.Companion.isNetworkAvailable

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
class LoadingView {

    private val context: Context
    private var rootView: FrameLayout? = null
    private var noDataView: View? = null
    private var loadingView: View? = null
    private var netInvalidView: View? = null

    constructor(activity: Activity) {
        context = activity
        rootView = activity.window.decorView as FrameLayout
    }

    constructor(fragment: Fragment) {
        context = fragment.activity
        rootView = fragment.activity.window.decorView as FrameLayout

    }


    private fun initNoDataView(viewId: Int = R.layout.view_no_data) {
        noDataView = ViewGroup.inflate(context, viewId, null)
        noDataView!!.layoutParams = getLayoutParams(context!!)
    }


    private fun initLoadingView(viewId: Int = R.layout.view_loading) {
        loadingView = ViewGroup.inflate(context, viewId, null)
        loadingView!!.layoutParams = getLayoutParams(context!!)
    }

    private fun initNetInvalidView(listener: View.OnClickListener?, viewId: Int = R.layout.view_net_invalid, clickViewId: Int = R.id.tv_reload) {
        netInvalidView = ViewGroup.inflate(context, viewId, null)
        netInvalidView!!.layoutParams = getLayoutParams(context!!)
        netInvalidView!!.findViewById<View>(clickViewId).setOnClickListener {

            if (!isNetworkAvailable(context!!)) {
                //亲，您现在没网
                toast("亲，现在您没网")
            } else {
                //有网从新请求网络
                if (listener != null)
                    listener!!.onClick(netInvalidView!!.findViewById<View>(clickViewId))
            }
        }
    }

    fun showOrHideLoadingView(isShow: Boolean) {
        if (isShow) {//先移除 防止重复添加导致程序崩溃
            if (loadingView == null) initLoadingView()
            //移除正在加载页面
            rootView!!.removeView(loadingView)
            //显示正在加载页面
            rootView!!.addView(loadingView)
        } else {
            //移除正在加载页面
            rootView!!.removeView(loadingView)
        }
    }

    /**
     * 展示或移除无数据视图
     *
     * @param isNoData 是否无数据
     */
    fun showOrHideNoDataView(isNoData: Boolean) {
        //无数据
        if (isNoData) {
            if (noDataView == null) initNoDataView()
            showOrHideLoadingView(false)
            //移除无数据页面 先移除再添加 防止重复添加导致程序崩溃
            rootView!!.removeView(noDataView)
            //显示无数据页面
            rootView!!.addView(noDataView)
        } else {
            //移除无数据页面
            rootView!!.removeView(noDataView)
        }
    }


    /**
     * 是否显示无网络视图
     *
     * @param isShowNoNet
     */
    fun showOrHideNoNetView(isShowNoNet: Boolean, listener: View.OnClickListener? = null) {


        if (isShowNoNet) {
            LogUtils.d("on_request_res")
            if (netInvalidView == null) initNetInvalidView(listener)
            //移除无网页面 先移除再添加 防止程序崩溃
            rootView!!.removeView(netInvalidView)
            //显示无网页面
            rootView!!.addView(netInvalidView)
        } else {
            //移除无网页面
            rootView!!.removeView(netInvalidView)
        }
    }

}
