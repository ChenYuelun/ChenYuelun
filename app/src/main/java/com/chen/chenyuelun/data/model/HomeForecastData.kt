package com.chen.chenyuelun.data.model

/**
 * Created by chenyuelun on 2018/2/3.
 */
 class HomeForecastData {


    lateinit var caiqiuFocusList: CaiqiuFocusList
    lateinit var topNavigationList: TopNavigationList
    lateinit var marqueeList: MarqueeList
    lateinit var hotMatchs: HotMatchs
    lateinit var recordList: RecordBean
    lateinit var advertisBean: AdvertisBean
    lateinit var guessYouLikeList: SeasonList
    val footballMatchs: MutableList<FootballItem> = mutableListOf()
    val basketballMatchs: MutableList<BasketballItem> = mutableListOf()
    var footballLiveUrl: String = ""
    var basketballLiveUrl: String = ""

}