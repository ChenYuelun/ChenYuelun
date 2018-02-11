package com.chen.chenyuelun.network

/**
 * Created by chenyuelun on 2018/1/20.
 */
class ParamsMapValue private constructor() {
    companion object {
        const val CAIQR_CLIENT_TYPE = 0

        val ONE = "1"
        val HOME = "home"
        val SPORTTERY = "sporttery"
        const val CAIQR_VERSION = "1.2"
        //闪屏倒计时接口
        const val CMD_GET_BANNER_ADVERTISE_MANAGEMENT = "getBannerAdvertiseManagement"
        //首页底部导航
        const val CMD_HOME_DYNAMIC_MENU = "home_dynamic_menu"
        // 首页目录
        const val CMD_HOME_CATALOG = "home_catalog"

        // 焦点图
        const val CMD_CAIQIU_FOCUS = "caiqiu_focus_04"

        //首页导航按钮上方
        const val CMD_TOP_NAVIGATE = "home_top_navigate"

        //跑马灯
        const val CMD_HOME_INSTANT_MESSAGE = "home_instant_message"

        // 热门比赛/焦点比赛
        const val CMD_HOME_HOT_MATCH = "home_hot_match"

        //足球直播
        const val CMD_FOOTBALL_MATCH_LIVE = "home_match_live"

        //篮球直播
        const val CMD_BASKETBALL_MATCH_LIVE = "home_basketball_match_live"

        //预测战绩
        const val CMD_HOME_RESULT = "home_result_statistics"

        // 猜你喜欢
        const val CMD_HOME_GUESS_YOU_LIKE = "home_guess_you_like"

        //首页赛事
        const val CMD_HOME_MATCH_LIST = "home_match_list"
        //首页广告位
        const val CMD_HOME_ADVERTIS = "home_advertis"
        //竞彩日历
        val CMD_FOOTBALL_DATE_SPORTTERY = "football_date_sporttery"
        //竞彩一天的比赛
        val CMD_FOOTBALL_LIST_SPORTTERY = "football_list_sporttery_02"
    }

}
