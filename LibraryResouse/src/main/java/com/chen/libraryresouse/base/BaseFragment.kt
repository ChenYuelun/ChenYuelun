package com.chen.libraryresouse.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chen.libraryresouse.costomView.LoadingView
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
abstract class BaseFragment : Fragment() {

    private var loadingView: LoadingView? = null

    abstract val isNetNecessary: Boolean //是否需要联网

    open var mPagename = ""

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(getLayoutId(), null, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
        initArguments()
        initView()
    }

    abstract fun getLayoutId(): Int
    protected fun initView() {}
    protected  fun initArguments() {}

    private fun setUp() {
        mPagename = this::class.simpleName!!//当前类名
        LogUtils.d("thisPageName", mPagename)
        loadingView = LoadingView(this)
        //需要联网获取数据
        judgeFirstHttp()

    }

    fun judgeFirstHttp() {
        if (!isNetNecessary) {//不需要网络请求
            return
        }
        if (isNetworkAvailable(context)) {
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