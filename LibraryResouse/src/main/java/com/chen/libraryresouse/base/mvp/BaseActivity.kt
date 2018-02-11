package com.chen.libraryresouse.base.mvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.chen.libraryresouse.utils.ActivityStack
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
abstract class BaseActiviy<V : IView,P : IPrsenter<V>> : AppCompatActivity() {

    open lateinit var mPagename :String
    open lateinit var mPresenter :P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mPagename = this::class.simpleName!!//当前类名
        //添加activity的管理器中
        ActivityStack.addActivity(this)
        mPresenter = createPresenter()
        mPresenter.attach(this as V)
        PhoneParameterUtils.setTitleLayout(this,getTitleLayout())
        initIntentData()
        setUp()
        readCahce()
    }

    abstract fun createPresenter(): P

    abstract fun getTitleLayout(): View ?

    abstract fun setUp()

    abstract fun getLayoutId(): Int


    open fun initIntentData() {}

    open fun readCahce() {}

    override fun onResume() {
        super.onResume()
        LogUtils.d("thisPage", mPagename)
    }

    override fun onDestroy() {
        super.onDestroy()

        mPresenter.detach()
        //从activity管理器中移除
        ActivityStack.removeActivity(this)
    }




}