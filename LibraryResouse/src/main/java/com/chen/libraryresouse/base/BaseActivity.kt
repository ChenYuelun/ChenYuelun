package com.chen.libraryresouse.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.chen.libraryresouse.costomView.LoadingView
import com.chen.libraryresouse.utils.ActivityStack
import com.chen.libraryresouse.utils.LogUtils
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
abstract class BaseActiviy : AppCompatActivity() {

    private var loadingView: LoadingView? = null

    abstract val isNetNecessary: Boolean //是否需要联网

    open var mPagename = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mPagename = this::class.simpleName!!//当前类名
        //添加activity的管理器中
        ActivityStack.addActivity(this)
        loadingView = LoadingView(this)
        initIntentData()
        initView()
        //需要联网获取数据
        judgeFirstHttp()
    }

    abstract fun getLayoutId(): Int

    abstract fun initView()

    open fun initIntentData() {}




    fun judgeFirstHttp() {
        if (!isNetNecessary) {//不需要网络请求
            return
        }
        if (isNetworkAvailable(this)) {
            showOrHideLoading(true)
            requestApi()
        } else {
            showOrHideNoNet(true)
        }

    }

    abstract fun requestApi()

    /**
     * 当请求数据成功调用此方法
     */
    open fun onRequestSuccess(hasData: Boolean) {
        //关闭无网/加载中/无数据视图
        showOrHideLoading(false)
        showOrHideNoNet(false)
        showOrHideNoData(!hasData)
    }

    /**
     * 当请求数据失败调用此方法
     */
    open fun onRequestError(hasData: Boolean) {
        showOrHideLoading(false)
        showOrHideNoData(false)
        showOrHideNoNet(!hasData)
    }

    open fun requestAgain() {
        showOrHideLoading(true)
    }

    override fun onResume() {
        super.onResume()
        judgeFirstHttp()
        LogUtils.d("thisPage", mPagename)
    }

    override fun onDestroy() {
        super.onDestroy()
        //从activity管理器中移除
        ActivityStack.removeActivity(this)
    }

    open fun showOrHideLoading(show: Boolean) {
        loadingView!!.showOrHideLoadingView(show)
    }

    open fun showOrHideNoData(show: Boolean) {
        loadingView!!.showOrHideNoDataView(show)
    }

    open fun showOrHideNoNet(show: Boolean) {
        loadingView!!.showOrHideNoNetView(show, View.OnClickListener { requestAgain() })
    }


}