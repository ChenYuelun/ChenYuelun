package com.chen.chenyuelun.presenter

import com.chen.chenyuelun.data.entity.*
import com.chen.chenyuelun.network.ParamsMapValue
import com.chen.chenyuelun.network.ParamsMapValue.Companion.CMD_CAIQIU_FOCUS
import com.chen.chenyuelun.network.ParamsMapValue.Companion.CMD_HOME_ADVERTIS
import com.chen.chenyuelun.network.ParamsMapValue.Companion.CMD_HOME_GUESS_YOU_LIKE
import com.chen.chenyuelun.network.ParamsMapValue.Companion.CMD_HOME_HOT_MATCH
import com.chen.chenyuelun.network.ParamsMapValue.Companion.CMD_HOME_INSTANT_MESSAGE
import com.chen.chenyuelun.network.ParamsMapValue.Companion.CMD_HOME_RESULT
import com.chen.chenyuelun.network.ParamsMapValue.Companion.CMD_TOP_NAVIGATE
import com.chen.chenyuelun.data.single.AppInfo
import com.chen.chenyuelun.network.request.*
import com.chen.chenyuelun.view.BaseView
import com.chen.librarynetwork.transformer.MyDefaultTransformer
import com.chen.libraryresouse.base.EnumForecastType
import com.chen.libraryresouse.utils.LogUtils
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by chenyuelun on 2018/2/2.
 */
class MainForecastPresenter(val view: BaseView, val data: HomeForecastData) : BasePresenter {

    override fun readDataFromCache() {
        if (AppInfo.instance.homeCatalog == null) {
            val catalogCache = AppInfo.instance.getCacheManager().getAsAny(ParamsMapValue.CMD_HOME_CATALOG)
            if (catalogCache != null && catalogCache is HomeCatalogResponse) {
                AppInfo.instance.homeCatalog = catalogCache.resp.toMutableList()
            } else {
                defaultCatalogData()
            }

        }
        defaultModelData()

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


    private fun defaultModelData() {
        val catalog = AppInfo.instance.homeCatalog
        if (catalog != null) {
            catalog.forEach {
                when (it.url) {
                    EnumForecastType.TYPE_BANNER.tag -> {
                        //缓存取数据
                        val bannerData = AppInfo.instance.getCacheManager().getAsAny(CMD_CAIQIU_FOCUS)
                        if (bannerData != null && bannerData is CaiqiuFocusResponse) {
                            //是否有数据及数据是否合法
                            data.caiqiuFocusList = bannerData.resp[0]
                        } else {
                            //否则使用默认数据
                            data.caiqiuFocusList = CaiqiuFocusList(1, listOf(CaiqiuFocusBean("", "", "", "http://7vzspj.com2.z0.glb.clouddn.com/image/focus/default_focus.png", "", "", "", "", "", "", "", 1, "", "", "", 1, 1, 1, Params(1, "", ""))))
                        }
                    }
                    EnumForecastType.TYPE_TOPNAVIGATION.tag -> {
                        val toPnavigationData = AppInfo.instance.getCacheManager().getAsAny(CMD_TOP_NAVIGATE)
                        if (toPnavigationData != null && toPnavigationData is TopNavigationResponse) {
                            data.topNavigationList = toPnavigationData.resp[0]
                        } else {
                            data.topNavigationList = TopNavigationList("顶部导航", listOf(), 2)
                        }
                    }
                    EnumForecastType.TYPE_MARQUEE.tag -> {
                        val marqueeData = AppInfo.instance.getCacheManager().getAsAny(CMD_HOME_INSTANT_MESSAGE)
                        if (marqueeData != null && marqueeData is MarqueeResponse) {
                            data.marqueeList = marqueeData.resp[0]
                        } else {
                            data.marqueeList = MarqueeList("跑马灯", listOf(), "", 4)
                        }
                    }
                    EnumForecastType.TYPE_ADVERTISING.tag -> {
                        val advertisData = AppInfo.instance.getCacheManager().getAsAny(CMD_HOME_ADVERTIS)
                        if (advertisData != null && advertisData is AdvertisResponse) {
                            data.advertisBean = advertisData.resp[0]
                        } else {
                            data.advertisBean = AdvertisBean("广告位", 4, "", "")
                        }
                    }
                    EnumForecastType.TYPE_HOT_MATCH.tag -> {
                        val hotMatchData = AppInfo.instance.getCacheManager().getAsAny(CMD_HOME_HOT_MATCH)
                        if (hotMatchData != null && hotMatchData is HotBettingResponse) {
                            data.hotMatchs = hotMatchData.resp[0]
                        } else {
                            data.hotMatchs = HotMatchs(5, 0, "热门比赛", "", "", listOf(), listOf())
                        }
                    }
                    EnumForecastType.TYPE_FOOT_LIVE.tag -> {
                        data.footballLiveUrl = it.backup
                    }
                    EnumForecastType.TYPE_BASKET_LIVE.tag -> {
                        data.footballLiveUrl = it.backup
                    }
                    EnumForecastType.TYPE_RECORD.tag -> {
                        val recordData = AppInfo.instance.getCacheManager().getAsAny(CMD_HOME_RESULT)
                        if (recordData != null && recordData is RecordResponse) {
                            data.recordList = recordData.resp[0]
                        } else {
                            data.recordList = RecordBean(8, "", "", Left("太准了", "没这么准的", "#ffffff"), Right("#ffffff", "没这么准的", "太装了", ""))
                        }
                    }
                    EnumForecastType.TYPE_GUESS_YOU_LIKE.tag -> {
                        val guessLikeData = AppInfo.instance.getCacheManager().getAsAny(CMD_HOME_GUESS_YOU_LIKE)
                        if (guessLikeData != null && guessLikeData is GuessYouLikeResponse) {
                            data.guessYouLikeList = guessLikeData.resp[0]
                        } else {
                            data.guessYouLikeList = SeasonList("猜你喜欢", listOf(), 9)
                        }
                    }
                }
            }
        }
    }

    override fun requestDataFromApi() {
        sendApiCaiqiuFocus()
        sendApiTopNavigation()
        sendApiMarquee()
        sendApiHotMatch()
        sendApiRecord()
        sendApiGuessYouLike()
        sendApiAdvertise()
        sendApiMatchs()
    }


    fun refreshData() {
        sendApiHomeCatalog()
    }

    fun refreshAllModelData() {
        AppInfo.instance.homeCatalog?.forEach {
            when (it.url) {


            }
        }

    }

    fun loadMore() {

    }

    //请求首页目录接口
    private fun sendApiHomeCatalog() {
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
                                refreshAllModelData()
                            } else {
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
                            if (it.code == 0) {
                                LogUtils.d("banner数据请求成功" + it.code)
                                data.caiqiuFocusList = it.resp[0]
                                AppInfo.instance.getCacheManager().put(ParamsMapValue.CMD_CAIQIU_FOCUS, it)

                            }
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
                            if (it.code == 0) {
                                LogUtils.d("top导航栏数据请求成功，code = " + it.code)
                                data.topNavigationList = it.resp[0]
                                AppInfo.instance.getCacheManager().put(ParamsMapValue.CMD_TOP_NAVIGATE, it)
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
                            if (it.code == 0) {
                                LogUtils.d("跑马灯数据请求成功，code = " + it.code)
                                data.marqueeList = it.resp[0]
                                AppInfo.instance.getCacheManager().put(ParamsMapValue.CMD_HOME_INSTANT_MESSAGE, it)
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
                            if (it.code == 0) {
                                LogUtils.d("热门比赛数据请求成功，code = " + it.code)
                                data.hotMatchs = it.resp[0]
                                AppInfo.instance.getCacheManager().put(ParamsMapValue.CMD_HOME_HOT_MATCH, it)
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
                            if (it.code == 0) {
                                LogUtils.d("战绩统计数据请求成功，code = " + it.code)
                                data.recordList = it.resp[0]
                                AppInfo.instance.getCacheManager().put(ParamsMapValue.CMD_HOME_RESULT, it)
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
                            if (it.code == 0) {
                                LogUtils.d("猜你喜欢数据请求成功，code = " + it.code)
                                data.guessYouLikeList = it.resp[0]
                                AppInfo.instance.getCacheManager().put(ParamsMapValue.CMD_HOME_GUESS_YOU_LIKE, it)
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
                            if (it.code == 0) {
                                LogUtils.d("广告位数据请求成功，code = " + it.code)
                                data.advertisBean = it.resp[0]
                                AppInfo.instance.getCacheManager().put(ParamsMapValue.CMD_HOME_ADVERTIS, it)

                            }
                        },
                        onError = {
                            it.printStackTrace()
                            LogUtils.d("广告位数据请求失败")
                        })
    }

    //请求首页比赛数据
    fun sendApiMatchs(lotteryCode: String = "0", latestId: String = "0") {
        val request = ForecastMatchListRequest(lotteryCode, latestId)
        ServiceFactory.createRxRetrofitService()
                .getHomeMatchList(request.getSign(), request.getRequestMap())
                .compose(MyDefaultTransformer<HomeMatchListResponse>())
                .subscribeBy(
                        onNext = {
                            if (it.code == 0) {
                                data.footballMatchs.clear()
                                data.footballMatchs.addAll(it.resp[0].football)
                                data.basketballMatchs.clear()
                                data.basketballMatchs.addAll(it.resp[0].basketball)
                                LogUtils.d("首页比赛数据请求成功，code = " + it.code)
                                AppInfo.instance.getCacheManager().put(ParamsMapValue.CMD_HOME_CATALOG, it)

                            }
                        },
                        onError = {
                            it.printStackTrace()
                            LogUtils.d("首页比赛数据请求失败")
                        })
    }


}