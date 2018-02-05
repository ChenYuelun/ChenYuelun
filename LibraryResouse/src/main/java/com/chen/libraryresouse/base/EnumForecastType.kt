package com.chen.libraryresouse.base

/**
 * Created by chenyuelun on 2018/2/3.
 */
enum class EnumForecastType(val tag :String ,val type :Int){

    TYPE_BANNER("caiqiu_focus_04",0),
    TYPE_TOPNAVIGATION("home_top_navigate",1),
    TYPE_MARQUEE("home_instant_message",2),
    TYPE_ADVERTISING("home_advertis",3),
    TYPE_HOT_MATCH("home_hot_match",4),
    TYPE_FOOT_LIVE("home_match_live",5),
    TYPE_BASKET_LIVE("home_basketball_match_live",6),
    TYPE_RECORD("home_result_statistics",7),
    TYPE_GUESS_YOU_LIKE("home_guess_you_like",8),
    TYPE_MATCH_TAB("home_match_list",9),
    TYPE_MATCHS("matchs",10),
    TYPE_NO_MATCHS("matchs",12);

}