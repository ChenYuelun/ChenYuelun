package com.chen.libraryresouse.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.chen.libraryresouse.utils.ActivityStack
import com.chen.libraryresouse.utils.LogUtils
import com.chen.libraryresouse.utils.PhoneParameterUtils

/**
 * Created by chenyuelun on 2018/2/9.
 */
abstract class BaseActiviy2 : AppCompatActivity() {
    open lateinit var mPagename :String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mPagename = this::class.simpleName!!//当前类名
        //添加activity的管理器中
        ActivityStack.addActivity(this)
        PhoneParameterUtils.setTitleLayout(this,getTitleLayout())
        initIntentData()
        setUp()
        readCahce()
    }
    abstract fun getTitleLayout(): View?

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
        //从activity管理器中移除
        ActivityStack.removeActivity(this)
    }




}