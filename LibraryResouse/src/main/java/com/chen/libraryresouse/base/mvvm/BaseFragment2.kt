package com.chen.libraryresouse.base.mvvm

import android.databinding.ViewDataBinding
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
abstract class BaseFragment2<B : ViewDataBinding,VM : BaseViewModel<B>> : Fragment() {
    open var mPagename = ""
    lateinit var mDataBinding: B
    lateinit var viewModel: VM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mDataBinding = getBanding(inflater,container)
        return mDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        PhoneParameterUtils.setTitleLayout(context!!, getTitleLyout())
        viewModel = createViewModel()
        viewModel.attch(mDataBinding)
        setUp()
        initArguments()
        initView()
    }

    abstract fun createViewModel(): VM

    abstract fun getBanding(inflater: LayoutInflater, container: ViewGroup?): B

    abstract fun getTitleLyout(): View?

    protected fun initView() {}

    protected fun initArguments() {}

    open fun setUp() {
        mPagename = this::class.simpleName!!//当前类名
    }


    override fun onResume() {
        super.onResume()
        LogUtils.d("thisPage", mPagename)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}