package com.chen.chenyuelun.model

import com.chen.chenyuelun.data.entity.HomeCatalogResponse
import com.chen.chenyuelun.data.entity.HomeMenuResponse
import com.chen.chenyuelun.data.single.AppApplication
import com.chen.chenyuelun.data.single.AppInfo
import com.chen.chenyuelun.network.ParamsMapValue
import com.chen.chenyuelun.network.request.GetBannerAdvertiseManagementRequest
import com.chen.chenyuelun.network.request.HomeCatalogrequest
import com.chen.chenyuelun.network.request.MainMenuRequest
import com.chen.librarynetwork.transformer.MyDefaultTransformer
import com.chen.libraryresouse.base.mvp.IModel
import com.chen.libraryresouse.utils.ImageLoader
import com.chen.libraryresouse.utils.LogUtils
import com.chen.libraryresouse.utils.toast
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by chenyuelun on 2018/2/8.
 */
class WelcomeModelmpI() : IModel<String> {

    override fun loadCache(listener: IModel.OnDataLoadListener<String>) {

    }

    override fun loadNetwork(listener: IModel.OnDataLoadListener<String>) {
        sendGetBannerAdvertiseManagementRequest()
        sendHomeMenuApi()
        sendApiHomeCatalog()
    }

    /**
     * Created by yyz on 01/06/2017.
     * 发送请求闪屏倒计时信息接口
     */
    private fun sendGetBannerAdvertiseManagementRequest() {
        val getBannerAdvertiseManagementRequest = GetBannerAdvertiseManagementRequest("xiaomi")
        ServiceFactory.createRxRetrofitService()
                .getBannerAdvertiseManagement(getBannerAdvertiseManagementRequest.getSign(), getBannerAdvertiseManagementRequest.getRequestMap())
                .compose(MyDefaultTransformer())
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
                                AppInfo.instance.getCacheManager().put(ParamsMapValue.CMD_HOME_DYNAMIC_MENU, it)
                                for (homeMenuText in it.resp[0].textList) {
                                    ImageLoader.savaBitmap(AppApplication.instance(),homeMenuText.normalUrl)
                                    ImageLoader.savaBitmap(AppApplication.instance(),homeMenuText.pressUrl)
                                }
                        },

                        onError = {
                            LogUtils.d("首页底部导航数据请求出错：" + it.message)
                            it.printStackTrace()
                            toast(it.message!!)
                        }


                )

    }

    //请求首页目录接口
    private fun sendApiHomeCatalog() {
        val request = HomeCatalogrequest()
        ServiceFactory.createRxRetrofitService()
                .getHomeCatalog(request.getSign(), request.getRequestMap())
                .compose(MyDefaultTransformer<HomeCatalogResponse>())
                .subscribeBy(
                        onNext = {
                            if (it.code == 0){
                                LogUtils.d("首页目录数据请求成功")
                                AppInfo.instance.homeCatalog = it.resp.toMutableList()
                                AppInfo.instance.getCacheManager().put(ParamsMapValue.CMD_HOME_CATALOG,it)
                            }else{
                                LogUtils.d("联网成功，数据失败")
                            }

                        },
                        onError = {
                            LogUtils.d("首页目录数据请求成功")
                        })
    }

    private fun sendVertifyCanBuy() {


    }

}