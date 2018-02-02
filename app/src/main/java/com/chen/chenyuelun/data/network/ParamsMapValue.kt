package com.chen.chenyuelun.data.network

/**
 * Created by chenyuelun on 2018/1/20.
 */
class ParamsMapValue private constructor() {
    companion object {
        const val CAIQR_CLIENT_TYPE = 0
        const val CAIQR_VERSION = "1.2"
        //闪屏倒计时接口
        const val CMD_GET_BANNER_ADVERTISE_MANAGEMENT = "getBannerAdvertiseManagement"
        //首页底部导航
        const val CMD_HOME_DYNAMIC_MENU = "home_dynamic_menu"
        // 首页目录
        const val CMD_HOME_CATALOG = "home_catalog"
    }
}
