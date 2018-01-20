package com.chen.chenyuelun

import android.os.Bundle
import com.chen.chenyuelun.network.RxSubscriber
import com.chen.chenyuelun.network.request.GetBannerAdvertiseManagementRequest
import com.chen.chenyuelun.network.response.GetBannerAdvertiseManagementResponse
import com.chen.librarynetwork.transformer.MyDefaultTransformer
import com.chen.libraryresouse.base.BaseActiviy
import com.chen.libraryresouse.utils.log
import com.chen.libraryresouse.utils.toast
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by chenyuelun on 2018/1/20.
 */
class WelcomeActivity : BaseActiviy() {

    override fun onCreate(savedInstanceState: Bundle?) {
        isNetNecessary = false
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sendGetBannerAdvertiseManagementRequest()
    }


    /**
     * Created by yyz on 01/06/2017.
     * 发送请求闪屏倒计时信息接口
     */
    private fun sendGetBannerAdvertiseManagementRequest() {
        val getBannerAdvertiseManagementRequest = GetBannerAdvertiseManagementRequest("xiaomi")
        val sub = RxSubscriber<GetBannerAdvertiseManagementResponse>(this)
        ServiceFactory.createRxRetrofitService()
                ?.get_banner_advertise_management(getBannerAdvertiseManagementRequest.getSign(), getBannerAdvertiseManagementRequest.getRequestMap())
                ?.compose(MyDefaultTransformer<GetBannerAdvertiseManagementResponse>())
                ?.subscribeBy(
                        onError = {
                            log("请求出错："+it.message)
                            it.printStackTrace()
                            toast("请求失败")
                        },
                        onNext = {
                            toast("请求成功")
                        })
    }

}

