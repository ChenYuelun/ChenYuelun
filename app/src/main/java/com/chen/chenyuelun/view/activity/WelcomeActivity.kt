package com.chen.chenyuelun.view.activity

import com.chen.chenyuelun.R
import com.chen.chenyuelun.presenter.WelcomePresenterImpi
import com.chen.chenyuelun.view.BaseView
import com.chen.libraryresouse.base.BaseActiviy
import com.chen.libraryresouse.constans.SPConstants
import com.chen.libraryresouse.utils.SPUtils

/**
 * Created by chenyuelun on 2018/1/20.
 */
class WelcomeActivity : BaseActiviy() ,BaseView{


    private lateinit var loginToken: String
    private lateinit var versionName: String
    private lateinit var  presenter :WelcomePresenterImpi

    override fun getLayoutId() = R.layout.activity_main

    override fun setUp() {
        presenter = WelcomePresenterImpi(this)
        versionName = SPUtils.getData(this, SPConstants.VERSION_CODE, "") as String
        loginToken = SPUtils.getData(this, SPConstants.LOGIN_TOKEN, "") as String
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

