package com.chen.chenyuelun.presenter

import com.chen.chenyuelun.data.model.*
import com.chen.chenyuelun.data.network.ParamsMapValue
import com.chen.chenyuelun.data.network.request.*
import com.chen.chenyuelun.data.single.AppInfo
import com.chen.chenyuelun.view.BaseView
import com.chen.librarynetwork.transformer.MyDefaultTransformer
import com.chen.libraryresouse.utils.LogUtils
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by chenyuelun on 2018/2/2.
 */
class MainForecastPresentet(val view: BaseView,val data :HomeForecastData) : BasePresenter {

    override fun readDataFromCache() {
        if (AppInfo.instance.homeCatalog == null) {
            val catalogCache = AppInfo.instance.getCacheManager().getAsAny(ParamsMapValue.CMD_HOME_CATALOG)
            if (catalogCache != null && catalogCache is HomeCatalogResponse) {
                AppInfo.instance.homeCatalog = catalogCache.resp.toMutableList()
            } else {
                defaultCatalogData()
            }

        }

    }

    private fun defaultCatalogData() {
        val defaultData = mutableListOf<HomeCatalogBean>()
        defaultData.add(HomeCatalogBean("caiqiu_focus_04", 1, "焦点图", ""))
        defaultData.add(HomeCatalogBean("home_top_navigate", 2, "首页上部导航", ""))
        defaultData.add(HomeCatalogBean("home_instant_message", 3, "跑马灯", ""))
        defaultData.add(HomeCatalogBean("home_result_statistics", 4, "预测战绩", ""))
        defaultData.add(HomeCatalogBean("home_guess_you_like", 5, "猜你喜欢", ""))
        defaultData.add(HomeCatalogBean("home_guess_you_like", 6, "首页赛事", ""))
        AppInfo.instance.homeCatalog = defaultData
    }

    override fun requestDataFromApi() {
        sendApiCaiqiuFocus()
        sendApiTopNavigation()
        sendApiMarquee()
        sendApiHotMatch()
        sendApiGuessYouLike()
        sendApiAdvertise()
        sendApiMatchs()
    }


    fun refreshData(){
        sendApiHomeCatalog()
    }

    fun refreshAllModelData(){
        AppInfo.instance.homeCatalog?.forEach {
            when(it.url){



            }
        }

    }

    fun loadMore(){

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
                                refreshAllModelData()
                            }else{
                                LogUtils.d("联网成功，数据失败")
                            }

                        },
                        onError = {
                            LogUtils.d("首页目录数据请求成功")
                        })
    }


    //请求banner数据
    fun sendApiCaiqiuFocus() {
        val request = CaiqiuFocusRequest()
        ServiceFactory.createRxRetrofitService()
                .getCaiqiuFocus(request.getSign(), request.getRequestMap())
                .compose(MyDefaultTransformer<CaiqiuFocusResponse>())
                .subscribeBy(
                        onNext = {
                            LogUtils.d("banner数据请求成功" + it.code)
                            data.caiqiuFocusList.clear()
                            data.caiqiuFocusList.addAll(it.resp.toMutableList())
                        },
                        onError = {
                            it.printStackTrace()
                            LogUtils.d("banner数据请求失败")
                        })
    }

    //请求top导航栏数据
    fun sendApiTopNavigation() {
        val request = TopNavigationRequest()
        ServiceFactory.createRxRetrofitService()
                .getTopNavigate(request.getSign(), request.getRequestMap())
                .compose(MyDefaultTransformer<TopNavigationResponse>())
                .subscribeBy(
                        onNext = {
                            if (it.code == 0){
                                LogUtils.d("top导航栏数据请求成功，code = " + it.code)
                                data.topNavigationList.clear()
                                data.topNavigationList.addAll(it.resp.toMutableList())
                            }
                        },
                        onError = {
                            it.printStackTrace()
                            LogUtils.d("top导航栏数据请求失败")
                        })
    }

    //请求跑马灯数据
    fun sendApiMarquee() {
        val request = MarqueeRequest()
        ServiceFactory.createRxRetrofitService()
                .getMarquee(request.getSign(), request.getRequestMap())
                .compose(MyDefaultTransformer<MarqueeResponse>())
                .subscribeBy(
                        onNext = {
                            if (it.code ==0){
                                LogUtils.d("跑马灯数据请求成功，code = " + it.code)
                                data.marqueeList.clear()
                                data.marqueeList.addAll(it.resp.toMutableList())
                            }
                        },
                        onError = {
                            it.printStackTrace()
                            LogUtils.d("跑马灯数据请求失败")
                        })
    }

    //请求热门比赛
    fun sendApiHotMatch() {
        val request = HotBetingRequest()
        ServiceFactory.createRxRetrofitService()
                .getHotBetting(request.getSign(), request.getRequestMap())
                .compose(MyDefaultTransformer<HotBettingResponse>())
                .subscribeBy(
                        onNext = {
                            if (it.code==0){
                                LogUtils.d("热门比赛数据请求成功，code = " + it.code)
                                data.hotMatchs.clear()
                                data.hotMatchs.addAll(it.resp.toMutableList())
                            }
                        },
                        onError = {
                            it.printStackTrace()
                            LogUtils.d("热门比赛数据请求失败")
                        })
    }

    //请求战绩统计
    fun sendApiRecord() {
        val request = RecordRequest()
        ServiceFactory.createRxRetrofitService()
                .getRecord(request.getSign(), request.getRequestMap())
                .compose(MyDefaultTransformer<RecordResponse>())
                .subscribeBy(
                        onNext = {
                            if (it.code == 0){
                                LogUtils.d("战绩统计数据请求成功，code = " + it.code)
                                data.recordList.clear()
                                data.recordList.addAll(it.resp.toMutableList())
                            }
                        },
                        onError = {
                            it.printStackTrace()
                            LogUtils.d("战绩统计数据请求失败")
                        })
    }

    //请求猜你喜欢
    fun sendApiGuessYouLike() {
        val request = GuessYouLikeRequest()
        ServiceFactory.createRxRetrofitService()
                .getGuessYouLike(request.getSign(), request.getRequestMap())
                .compose(MyDefaultTransformer<GuessYouLikeResponse>())
                .subscribeBy(
                        onNext = {
                            if (it.code == 0){
                                LogUtils.d("猜你喜欢数据请求成功，code = " + it.code)
                                data.guessYouLikeList.clear()
                                data.guessYouLikeList.addAll(it.resp.toMutableList())
                            }
                        },
                        onError = {
                            it.printStackTrace()
                            LogUtils.d("猜你喜欢数据请求失败")
                        })
    }

    //请求广告位
    fun sendApiAdvertise() {
        val request = AdvertisRequest()
        ServiceFactory.createRxRetrofitService()
                .getHomeAdvertis(request.getSign(), request.getRequestMap())
                .compose(MyDefaultTransformer<AdvertisResponse>())
                .subscribeBy(
                        onNext = {
                            LogUtils.d("广告位数据请求成功，code = " + it.code)
                        },
                        onError = {
                            it.printStackTrace()
                            LogUtils.d("广告位数据请求失败")
                        })
    }

    //请求首页比赛数据
    fun sendApiMatchs(lotteryCode: String = "0", latestId: String = "0") {
        val request = ForecastMatchListRequest(lotteryCode,latestId)
        ServiceFactory.createRxRetrofitService()
                .getHomeMatchList(request.getSign(), request.getRequestMap())
                .compose(MyDefaultTransformer<HomeMatchListResponse>())
                .subscribeBy(
                        onNext = {
                            LogUtils.d("首页比赛数据请求成功，code = " + it.code)
                        },
                        onError = {
                            it.printStackTrace()
                            LogUtils.d("首页比赛数据请求失败")
                        })
    }



}