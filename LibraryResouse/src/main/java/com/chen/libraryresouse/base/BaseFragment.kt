package com.chen.libraryresouse.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chen.libraryresouse.utils.LogUtils
import com.chen.libraryresouse.utils.PhoneParameterUtils


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
abstract class BaseFragment<V,P : IPrsenter<V>> : Fragment() {

    open var mPagename = ""
    open lateinit var mPresenter :P

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(),null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter = createPresenter()
        mPresenter.attach(this as V)
        PhoneParameterUtils.setTitleLayout(context!!,getTitleLyout())
        setUp()
        initArguments()
        initView()
    }

    abstract fun createPresenter(): P

    abstract fun getTitleLyout(): View?


    abstract fun getLayoutId(): Int

    protected fun initView() {}

    protected fun initArguments() {}

    open fun setUp() {
        mPagename = this::class.simpleName!!//当前类名
        LogUtils.d("thisPageName", mPagename)
    }


    override fun onResume() {
        super.onResume()
        LogUtils.d("thisPage", mPagename)
    }

    override fun onDestroy() {
        mPresenter.detach()
        super.onDestroy()
    }
}