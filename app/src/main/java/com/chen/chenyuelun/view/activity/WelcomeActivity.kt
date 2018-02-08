package com.chen.chenyuelun.view.activity

import android.os.Handler
import android.view.View
import com.chen.chenyuelun.R
import com.chen.chenyuelun.presenter.WelcomePresenter
import com.chen.chenyuelun.view.BaseView
import com.chen.libraryresouse.base.BaseActiviy
import com.chen.libraryresouse.base.IView
import com.chen.libraryresouse.constans.SPConstants
import com.chen.libraryresouse.utils.SPUtils

/**
 * Created by chenyuelun on 2018/1/20.
 */
class WelcomeActivity : BaseActiviy<IView, WelcomePresenter<IView>>(), IView {


    private lateinit var loginToken: String
    private lateinit var versionName: String


    override fun createPresenter(): WelcomePresenter<IView> {

        return WelcomePresenter()

    }

    override fun getTitleLayout() = null

    override fun setUp() {
        versionName = SPUtils.getData(this, SPConstants.VERSION_CODE, "") as String
        loginToken = SPUtils.getData(this, SPConstants.LOGIN_TOKEN, "") as String
    }


    override fun getLayoutId() = R.layout.activity_welcome

    override fun showLoading() {
    }

    override fun showData(data: Any) {

    }


    override fun onResume() {
        super.onResume()
        val handler = Handler()
        handler.postDelayed({ jumpToMain() }, 2000)
    }

    private fun jumpToMain() {
        MainActivity.start(this)
        finish()
    }


}

