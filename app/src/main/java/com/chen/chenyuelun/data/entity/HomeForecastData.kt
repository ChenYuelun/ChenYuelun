package com.chen.chenyuelun.data.entity

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
    val footballMatchs = FootballMatchs()
    val basketballMatchs = BasketballMatchs()
    val footballLiveUrl = FootballLiveUrl("")
    val basketballLiveUrl = BasketballLiveUrl("")

}

data class FootballMatchs(
        val matchs: MutableList<FootballItem> = mutableListOf()
)

data class BasketballMatchs(
        val matchs: MutableList<BasketballItem> = mutableListOf()
)


data class FootballLiveUrl(
        var url: String
)

data class BasketballLiveUrl(
        var url: String
)