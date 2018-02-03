package com.chen.chenyuelun.view.activity

import android.os.Handler
import com.chen.chenyuelun.R
import com.chen.chenyuelun.presenter.WelcomePresenter
import com.chen.chenyuelun.view.BaseView
import com.chen.libraryresouse.base.BaseActiviy
import com.chen.libraryresouse.constans.SPConstants
import com.chen.libraryresouse.utils.SPUtils

/**
 * Created by chenyuelun on 2018/1/20.
 */
class WelcomeActivity : BaseActiviy(), BaseView {



    private lateinit var loginToken: String
    private lateinit var versionName: String
    private lateinit var presenter: WelcomePresenter

    override fun getLayoutId() = R.layout.activity_welcome

    override fun getTitleLayout() = null

    override fun setUp() {
        presenter = WelcomePresenter(this)
        versionName = SPUtils.getData(this, SPConstants.VERSION_CODE, "") as String
        loginToken = SPUtils.getData(this, SPConstants.LOGIN_TOKEN, "") as String
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

    override fun readCahce() {
        super.readCahce()
        presenter.readDataFromCache()
    }

    override fun requestApi() {
        presenter.requestDataFromApi()
    }


    override fun onError(hasData: Boolean) {
    }

    override fun onSeccuess() {
    }


}

