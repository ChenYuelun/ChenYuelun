package com.chen.chenyuelun.model

import com.chen.chenyuelun.data.entity.*
import com.chen.chenyuelun.data.single.AppInfo
import com.chen.chenyuelun.network.ParamsMapValue
import com.chen.chenyuelun.network.request.*
import com.chen.librarynetwork.transformer.MyDefaultTransformer
import com.chen.libraryresouse.base.EnumForecastType
import com.chen.libraryresouse.base.mvp.IModel
import com.chen.libraryresouse.utils.LogUtils
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by chenyuelun on 2018/2/8.
 */
class MainForecastModelmpl : IModel<Any> {

    override fun loadCache(listener: IModel.OnDataLoadListener<Any>) {
        readDataFromCache(listener)

    }

    override fun loadNetwork(listener: IModel.OnDataLoadListener<Any>) {
        refreshAllModelDataFromLogcata(listener)
    }


    private fun readDataFromCache(listener: IModel.OnDataLoadListener<Any>) {
        if (AppInfo.instance.homeCatalog == null) {
            val catalogCache = AppInfo.instance.getCacheManager().getAsAny(ParamsMapValue.CMD_HOME_CATALOG)
            if (catalogCache != null && catalogCache is HomeCatalogResponse) {
                AppInfo.instance.homeCatalog = catalogCache.resp.toMutableList()
            } else {
                defaultCatalogData()
            }

        }
        defaultModelData(listener)
    }


    private fun defaultCatalogData() {
        val defaultData = mutableListOf<HomeCatalogBean>()
        defaultData.add(HomeCatalogBean("caiqiu_focus_04", 1, "焦点图", ""))
        defaultData.add(HomeCatalogBean("home_top_navigate", 2, "首页上部导航", ""))
        defaultData.add(HomeCatalogBean("home_instant_message", 3, "跑马灯", ""))
        defaultData.add(HomeCatalogBean("home_result_statistics", 4, "预测战绩", ""))
        defaultData.add(HomeCatalogBean("home_guess_you_like", 5, "猜你喜欢", ""))
        defaultData.add(HomeCatalogBean("home_match_list", 6, "首页赛事", ""))
        defaultData.sortBy { it.order.dec() }
        AppInfo.instance.homeCatalog = defaultData
    }


    private fun defaultModelData(listener: IModel.OnDataLoadListener<Any>) {
        val catalog = AppInfo.instance.homeCatalog
        if (catalog != null) {
            catalog.forEach {
                when (it.url) {
                    EnumForecastType.TYPE_BANNER.tag -> {
                        //缓存取数据
                        val banner: CaiqiuFocusList
                        val bannerData = AppInfo.instance.getCacheManager().getAsAny(ParamsMapValue.CMD_CAIQIU_FOCUS)
                        if (bannerData != null && bannerData is CaiqiuFocusResponse) {
                            //是否有数据及数据是否合法
                            banner = bannerData.resp[0]

                        } else {
                            //否则使用默认数据
                            banner = CaiqiuFocusList(1, listOf(CaiqiuFocusBean("", "", "", "http://7vzspj.com2.z0.glb.clouddn.com/image/focus/default_focus.png", "", "", "", "", "", "", "", 1, "", "", "", 1, 1, 1, Params(1, "", ""))))
                        }
                        listener.onComplete(banner)
                    }
                    EnumForecastType.TYPE_TOPNAVIGATION.tag -> {
                        val data: TopNavigationList
                        val toPnavigationData = AppInfo.instance.getCacheManager().getAsAny(ParamsMapValue.CMD_TOP_NAVIGATE)
                        if (toPnavigationData != null && toPnavigationData is TopNavigationResponse) {
                            data = toPnavigationData.resp[0]
                        } else {
                            data = TopNavigationList("顶部导航", listOf(), 2)
                        }
                        listener.onComplete(data)
                    }
                    EnumForecastType.TYPE_MARQUEE.tag -> {
                        val data :MarqueeList
                        val marqueeData = AppInfo.instance.getCacheManager().getAsAny(ParamsMapValue.CMD_HOME_INSTANT_MESSAGE)
                        if (marqueeData != null && marqueeData is MarqueeResponse) {
                            data = marqueeData.resp[0]
                        } else {
                            data = MarqueeList("跑马灯", listOf(), "", 4)
                        }
                        listener.onComplete(data)
                    }
                    EnumForecastType.TYPE_ADVERTISING.tag -> {
                        val data :AdvertisBean
                        val advertisData = AppInfo.instance.getCacheManager().getAsAny(ParamsMapValue.CMD_HOME_ADVERTIS)
                        if (advertisData != null && advertisData is AdvertisResponse) {
                            data = advertisData.resp[0]
                        } else {
                            data = AdvertisBean("广告位", 4, "", "")
                        }
                        listener.onComplete(data)
                    }
                    EnumForecastType.TYPE_HOT_MATCH.tag -> {
                        val data :HotMatchs
                        val hotMatchData = AppInfo.instance.getCacheManager().getAsAny(ParamsMapValue.CMD_HOME_HOT_MATCH)
                        if (hotMatchData != null && hotMatchData is HotBettingResponse) {
                            data = hotMatchData.resp[0]
                        } else {
                            data = HotMatchs(5, 0, "热门比赛", "", "", listOf(), listOf())
                        }
                        listener.onComplete(data)
                    }
                    EnumForecastType.TYPE_FOOT_LIVE.tag -> {
                        listener.onComplete(FootballLiveUrl(it.backup))
                    }
                    EnumForecastType.TYPE_BASKET_LIVE.tag -> {
                        listener.onComplete(BasketballLiveUrl(it.backup))
                    }
                    EnumForecastType.TYPE_RECORD.tag -> {
                        val data :RecordBean
                        val recordData = AppInfo.instance.getCacheManager().getAsAny(ParamsMapValue.CMD_HOME_RESULT)
                        if (recordData != null && recordData is RecordResponse) {
                            data = recordData.resp[0]
                        } else {
                            data = RecordBean(8, "", "", Left("太准了", "没这么准的", "#ffffff"), Right("#ffffff", "没这么准的", "太装了", ""))
                        }
                        listener.onComplete(data)
                    }
                    EnumForecastType.TYPE_GUESS_YOU_LIKE.tag -> {
                        val data :SeasonList
                        val guessLikeData = AppInfo.instance.getCacheManager().getAsAny(ParamsMapValue.CMD_HOME_GUESS_YOU_LIKE)
                        if (guessLikeData != null && guessLikeData is GuessYouLikeResponse) {
                            data = guessLikeData.resp[0]
                        } else {
                            data = SeasonList("猜你喜欢", listOf(), 9)
                        }
                        listener.onComplete(data)
                    }
                }
            }
        }
    }


    //先请求目录数据 目录数据返回后再刷新各模块
    fun refreshData(listener: IModel.OnDataLoadListener<Any>) {
        sendApiHomeCatalog(listener)
    }

    fun refreshAllModelDataFromLogcata(listener: IModel.OnDataLoadListener<Any>) {
        AppInfo.instance.homeCatalog?.forEach {
            when (it.url) {
                EnumForecastType.TYPE_BANNER.tag -> sendApiCaiqiuFocus(listener)
                EnumForecastType.TYPE_TOPNAVIGATION.tag -> sendApiTopNavigation(listener)
                EnumForecastType.TYPE_MARQUEE.tag -> sendApiMarquee(listener)
                EnumForecastType.TYPE_ADVERTISING.tag -> sendApiAdvertise(listener)
                EnumForecastType.TYPE_HOT_MATCH.tag -> sendApiHotMatch(listener)
                EnumForecastType.TYPE_FOOT_LIVE.tag -> refreshFootLive(listener)
                EnumForecastType.TYPE_BASKET_LIVE.tag -> refreshBasketLive(listener)
                EnumForecastType.TYPE_RECORD.tag -> sendApiRecord(listener)
                EnumForecastType.TYPE_GUESS_YOU_LIKE.tag -> sendApiGuessYouLike(listener)
                EnumForecastType.TYPE_MATCH_LIST.tag -> sendApiMatchs(listener)
            }
        }

    }

    private fun refreshBasketLive(listener: IModel.OnDataLoadListener<Any>) {

    }

    private fun refreshFootLive(listener: IModel.OnDataLoadListener<Any>) {

    }

    fun loadMore() {

    }

    //请求首页目录接口
    private fun sendApiHomeCatalog(listener: IModel.OnDataLoadListener<Any>) {
        val request = HomeCatalogrequest()
        ServiceFactory.createRxRetrofitService()
                .getHomeCatalog(request.getSign(), request.getRequestMap())
                .compose(MyDefaultTransformer<HomeCatalogResponse>())
                .subscribeBy(
                        onNext = {
                            if (it.code == 0) {
                                LogUtils.d("首页目录数据请求成功")
                                AppInfo.instance.homeCatalog = it.resp.toMutableList()
                                AppInfo.instance.getCacheManager().put(ParamsMapValue.CMD_HOME_CATALOG, it)
                                refreshAllModelDataFromLogcata(listener)
                            } else {
                                LogUtils.d("联网成功，数据失败")
                                refreshAllModelDataFromLogcata(listener)
                            }

                        },
                        onError = {
                            LogUtils.d("首页目录数据请求成功")
                            refreshAllModelDataFromLogcata(listener)
                        })
    }


    //请求banner数据
    fun sendApiCaiqiuFocus(listener: IModel.OnDataLoadListener<Any>) {
        val request = CaiqiuFocusRequest()
        ServiceFactory.createRxRetrofitService()
                .getCaiqiuFocus(request.getSign(), request.getRequestMap())
                .compose(MyDefaultTransformer<CaiqiuFocusResponse>())
                .subscribeBy(
                        onNext = {
                            if (it.code == 0) {
                                LogUtils.d("banner数据请求成功" + it.code)
//                                data.caiqiuFocusList =
                                listener.onComplete(it.resp[0])
                                AppInfo.instance.getCacheManager().put(ParamsMapValue.CMD_CAIQIU_FOCUS, it)

                            }
                        },
                        onError = {
                            it.printStackTrace()
                            LogUtils.d("banner数据请求失败")
                        })
    }

    //请求top导航栏数据
    fun sendApiTopNavigation(listener: IModel.OnDataLoadListener<Any>) {
        val request = TopNavigationRequest()
        ServiceFactory.createRxRetrofitService()
                .getTopNavigate(request.getSign(), request.getRequestMap())
                .compose(MyDefaultTransformer<TopNavigationResponse>())
                .subscribeBy(
                        onNext = {
                            if (it.code == 0) {
                                LogUtils.d("top导航栏数据请求成功，code = " + it.code)
//                                data.topNavigationList = it.resp[0]
                                listener.onComplete(it.resp[0])
                                AppInfo.instance.getCacheManager().put(ParamsMapValue.CMD_TOP_NAVIGATE, it)
                            }
                        },
                        onError = {
                            it.printStackTrace()
                            LogUtils.d("top导航栏数据请求失败")
                        })
    }

    //请求跑马灯数据
    fun sendApiMarquee(listener: IModel.OnDataLoadListener<Any>) {
        val request = MarqueeRequest()
        ServiceFactory.createRxRetrofitService()
                .getMarquee(request.getSign(), request.getRequestMap())
                .compose(MyDefaultTransformer<MarqueeResponse>())
                .subscribeBy(
                        onNext = {
                            if (it.code == 0) {
                                LogUtils.d("跑马灯数据请求成功，code = " + it.code)
//                                data.marqueeList = it.resp[0]
                                listener.onComplete(it.resp[0])
                                AppInfo.instance.getCacheManager().put(ParamsMapValue.CMD_HOME_INSTANT_MESSAGE, it)
                            }
                        },
                        onError = {
                            it.printStackTrace()
                            LogUtils.d("跑马灯数据请求失败")
                        })
    }

    //请求热门比赛
    fun sendApiHotMatch(listener: IModel.OnDataLoadListener<Any>) {
        val request = HotBetingRequest()
        ServiceFactory.createRxRetrofitService()
                .getHotBetting(request.getSign(), request.getRequestMap())
                .compose(MyDefaultTransformer<HotBettingResponse>())
                .subscribeBy(
                        onNext = {
                            if (it.code == 0) {
                                LogUtils.d("热门比赛数据请求成功，code = " + it.code)
//                                data.hotMatchs = it.resp[0]
                                listener.onComplete(it.resp[0])
                                AppInfo.instance.getCacheManager().put(ParamsMapValue.CMD_HOME_HOT_MATCH, it)
                            }
                        },
                        onError = {
                            it.printStackTrace()
                            LogUtils.d("热门比赛数据请求失败")
                        })
    }

    //请求战绩统计
    fun sendApiRecord(listener: IModel.OnDataLoadListener<Any>) {
        val request = RecordRequest()
        ServiceFactory.createRxRetrofitService()
                .getRecord(request.getSign(), request.getRequestMap())
                .compose(MyDefaultTransformer<RecordResponse>())
                .subscribeBy(
                        onNext = {
                            if (it.code == 0) {
                                LogUtils.d("战绩统计数据请求成功，code = " + it.code)
//                                data.recordList = it.resp[0]
                                listener.onComplete(it.resp[0])
                                AppInfo.instance.getCacheManager().put(ParamsMapValue.CMD_HOME_RESULT, it)
                            }
                        },
                        onError = {
                            it.printStackTrace()
                            LogUtils.d("战绩统计数据请求失败")
                        })
    }

    //请求猜你喜欢
    fun sendApiGuessYouLike(listener: IModel.OnDataLoadListener<Any>) {
        val request = GuessYouLikeRequest()
        ServiceFactory.createRxRetrofitService()
                .getGuessYouLike(request.getSign(), request.getRequestMap())
                .compose(MyDefaultTransformer<GuessYouLikeResponse>())
                .subscribeBy(
                        onNext = {
                            if (it.code == 0) {
                                LogUtils.d("猜你喜欢数据请求成功，code = " + it.code)
//                                data.guessYouLikeList = it.resp[0]
                                listener.onComplete(it.resp[0])
                                AppInfo.instance.getCacheManager().put(ParamsMapValue.CMD_HOME_GUESS_YOU_LIKE, it)
                            }
                        },
                        onError = {
                            it.printStackTrace()
                            LogUtils.d("猜你喜欢数据请求失败")
                        })
    }

    //请求广告位
    fun sendApiAdvertise(listener: IModel.OnDataLoadListener<Any>) {
        val request = AdvertisRequest()
        ServiceFactory.createRxRetrofitService()
                .getHomeAdvertis(request.getSign(), request.getRequestMap())
                .compose(MyDefaultTransformer<AdvertisResponse>())
                .subscribeBy(
                        onNext = {
                            if (it.code == 0) {
                                LogUtils.d("广告位数据请求成功，code = " + it.code)
//                                data.advertisBean = it.resp[0]
                                listener.onComplete(it.resp[0])
                                AppInfo.instance.getCacheManager().put(ParamsMapValue.CMD_HOME_ADVERTIS, it)

                            }
                        },
                        onError = {
                            it.printStackTrace()
                            LogUtils.d("广告位数据请求失败")
                        })
    }

    var isFoot = true
    var isLoadMore = false
    //请求首页比赛数据
    fun sendApiMatchs(listener: IModel.OnDataLoadListener<Any>, lotteryCode: String = "0", latestId: String = "0") {
        val request = ForecastMatchListRequest(lotteryCode, latestId)
        ServiceFactory.createRxRetrofitService()
                .getHomeMatchList(request.getSign(), request.getRequestMap())
                .compose(MyDefaultTransformer<HomeMatchListResponse>())
                .subscribeBy(
                        onNext = {
                            if (it.code == 0) {
//                                data.footballMatchs.matchs.clear()
//                                data.footballMatchs.matchs.addAll(it.resp[0].football)
//                                data.basketballMatchs.matchs.clear()
//                                data.basketballMatchs.matchs.addAll(it.resp[0].basketball)
                                LogUtils.d("首页比赛数据请求成功，code = " + it.code)
                                listener.onComplete(FootballMatchs(it.resp[0].football.toMutableList()))
                                listener.onComplete(BasketballMatchs(it.resp[0].basketball.toMutableList()))
                                AppInfo.instance.getCacheManager().put(ParamsMapValue.CMD_HOME_CATALOG, it)

                            }
                        },
                        onError = {
                            it.printStackTrace()
                            LogUtils.d("首页比赛数据请求失败")
                        })
    }

}