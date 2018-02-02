package com.chen.chenyuelun.presenter

import com.chen.chenyuelun.data.model.GetBannerAdvertiseManagementResponse
import com.chen.chenyuelun.data.model.HomeMenuResponse
import com.chen.chenyuelun.data.network.request.GetBannerAdvertiseManagementRequest
import com.chen.chenyuelun.data.network.request.MainMenuRequest
import com.chen.chenyuelun.utils.NavigationBarUtils
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
        sendHomeMenuApi()
        sendGetBannerAdvertiseManagementRequest()
    }


    /**
     * Created by yyz on 01/06/2017.
     * 发送请求闪屏倒计时信息接口
     */
    private fun sendGetBannerAdvertiseManagementRequest() {
        val getBannerAdvertiseManagementRequest = GetBannerAdvertiseManagementRequest("xiaomi")
        ServiceFactory.createRxRetrofitService()
                .getBannerAdvertiseManagement(getBannerAdvertiseManagementRequest.getSign(), getBannerAdvertiseManagementRequest.getRequestMap())
                .compose(MyDefaultTransformer<GetBannerAdvertiseManagementResponse>())
                .subscribeBy(
                        onError = {
                            LogUtils.d("闪屏请求出错：" + it.message)
                            it.printStackTrace()
                            toast(it.message!!)
                        },
                        onNext = {
                            LogUtils.d("闪屏请求成功")
                        })
    }



    private fun sendHomeMenuApi() {
        val homeMenuRequest = MainMenuRequest()
        ServiceFactory.createRxRetrofitService()
                .getHomeMenu(homeMenuRequest.getSign(), homeMenuRequest.getRequestMap())
                .compose(MyDefaultTransformer<HomeMenuResponse>())
                .subscribeBy(
                        onNext = {
                            LogUtils.d("首页底部导航数据请求成功")
                            NavigationBarUtils.instance.savaData(it)
                        },

                        onError = {
                            LogUtils.d("首页底部导航数据请求出错：" + it.message)
                            it.printStackTrace()
                            toast(it.message!!)
                        }


                )

    }

    private fun sendVertifyCanBuy() {


    }

}