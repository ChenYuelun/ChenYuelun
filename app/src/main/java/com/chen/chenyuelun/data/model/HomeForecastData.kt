package com.chen.chenyuelun.data.model

/**
 * Created by chenyuelun on 2018/2/3.
 */
data class HomeForecastData(
        val caiqiuFocusList : MutableList<CaiqiuFocusList> = mutableListOf(),
        val topNavigationList: MutableList<TopNavigationList> = mutableListOf(),
        val marqueeList: MutableList<MarqueeList> = mutableListOf(),
        val hotMatchs: MutableList<HotMatchs> = mutableListOf(),
        val recordList: MutableList<RecordBean> = mutableListOf(),
        val seasonList: MutableList<SeasonList> = mutableListOf(),
        val advertisBean: MutableList<AdvertisBean> = mutableListOf(),
        val guessYouLikeList:MutableList<SeasonList> = mutableListOf(),
        val footballMatchs: MutableList<FootballItem> = mutableListOf(),
        val basketballMatchs: MutableList<BasketballItem> = mutableListOf()

)