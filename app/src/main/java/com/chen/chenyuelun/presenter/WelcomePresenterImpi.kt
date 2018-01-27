package com.chen.chenyuelun.presenter

import com.chen.chenyuelun.data.model.GetBannerAdvertiseManagementResponse
import com.chen.chenyuelun.data.network.request.GetBannerAdvertiseManagementRequest
import com.chen.chenyuelun.view.BaseView
import com.chen.librarynetwork.transformer.MyDefaultTransformer
import com.chen.libraryresouse.utils.LogUtils
import com.chen.libraryresouse.utils.toast
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by chenyuelun on 2018/1/27.
 */
class WelcomePresenterImpi (viewImp : BaseView) :BasePresenter{

    val view = viewImp

    override fun readDataFromCache() {

    }

    override fun requestDataFromApi() {
        sendGetBannerAdvertiseManagementRequest()
    }


    /**
     * Created by yyz on 01/06/2017.
     * 发送请求闪屏倒计时信息接口
     */
    private fun sendGetBannerAdvertiseManagementRequest() {
        val getBannerAdvertiseManagementRequest = GetBannerAdvertiseManagementRequest("xiaomi")
        ServiceFactory.createRxRetrofitService()
                .get_banner_advertise_management(getBannerAdvertiseManagementRequest.getSign(), getBannerAdvertiseManagementRequest.getRequestMap())
                .compose(MyDefaultTransformer<GetBannerAdvertiseManagementResponse>())
                .subscribeBy(
                        onError = {
                            LogUtils.d("请求出错：" + it.message)
                            it.printStackTrace()
                            toast("请求失败")
                        },
                        onNext = {
                            toast("请求成功")
                        })
    }


    private fun sendVertifyCanBuy() {


    }

}