package com.chen.chenyuelun.view.activity

import com.chen.chenyuelun.R
import com.chen.chenyuelun.data.network.request.GetBannerAdvertiseManagementRequest
import com.chen.chenyuelun.data.model.GetBannerAdvertiseManagementResponse
import com.chen.librarynetwork.transformer.MyDefaultTransformer
import com.chen.libraryresouse.base.BaseActiviy
import com.chen.libraryresouse.constans.SPConstants
import com.chen.libraryresouse.utils.LogUtils
import com.chen.libraryresouse.utils.SpUtils
import com.chen.libraryresouse.utils.toast
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by chenyuelun on 2018/1/20.
 */
class WelcomeActivity : BaseActiviy() {

    private lateinit var loginToken: String
    private lateinit var versionName: String

    override fun getLayoutId() = R.layout.activity_main

    override fun initView() {

    }

    override fun initIntentData() {
        versionName = SpUtils.getData(this, SPConstants.VERSION_CODE, "") as String
        loginToken = SpUtils.getData(this, SPConstants.LOGIN_TOKEN, "") as String
    }


    override fun requestApi() {
        sendVertifyCanBuy()
        sendGetBannerAdvertiseManagementRequest()
    }


    private fun sendVertifyCanBuy() {


    }


    /**
     * Created by yyz on 01/06/2017.
     * 发送请求闪屏倒计时信息接口
     */
    private fun sendGetBannerAdvertiseManagementRequest() {
        val getBannerAdvertiseManagementRequest = GetBannerAdvertiseManagementRequest("xiaomi")
        ServiceFactory.createRxRetrofitService()
                ?.get_banner_advertise_management(getBannerAdvertiseManagementRequest.getSign(), getBannerAdvertiseManagementRequest.getRequestMap())
                ?.compose(MyDefaultTransformer<GetBannerAdvertiseManagementResponse>())
                ?.subscribeBy(
                        onError = {
                            LogUtils.d("请求出错：" + it.message)
                            it.printStackTrace()
                            toast("请求失败")
                        },
                        onNext = {
                            toast("请求成功")
                        })
    }

}

