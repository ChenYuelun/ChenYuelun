package com.chen.libraryresouse.base

/**
 * Created by chenyuelun on 2018/1/20.
 */
class BaseUrl {
    companion object {
        //**************************** API 地址 **************************************
        /**
         * cvt环境。
         */
        const val BASE_URL_A = "https://api.caiqr.cn/";

        /**
         * debug环境
         */
//    public static final String BASE_URL_A = "https://debug.caiqr.com/";
//        val BASE_URL_A = "https://europe.caiqr.com/"
        /**
         * 正式环境
         */
//    public static final String BASE_URL_A = "https://api.caiqr.com/";

        /**
         * trunk环境 cvt
         */
//    public static final String BASE_URL_A = "https://trunk.caiqr.cn/";
        /**
         * trunk环境 debug
         */
//    public static final String BASE_URL_A = "https://trunk.caiqr.com/";

//**************************** 篮球直播 API 地址 **************************************


        /**
         * 篮球直播的地址 cvt
         */
        val BASE_URL_B = "https://api.caiqr.cn/live/"
        /**
         * 篮球直播的地址 release
         */
//    public static final String BASE_URL_B = "https://live.caiqr.com/live/";

        /**
         * WebSocket服务端的url的测试地址
         */
        val WEB_SOCKET_URL = "ws://api.caiqr.cn:9090/websocket"
        /**
         * WebSocket服务端的url的正式地址
         */
//    public static final String WEB_SOCKET_URL = "ws://socket.caiqr.com:9090/websocket";

//**************************** 红包购买列表 H5 地址 **************************************
        /**
         * 购买红包H5的加载地址测试地址321
         */
        val RED_LOAD_URL = "https://test.caiqr.cn/jingcai/userCenter/redPurchase.html"

        /**
         * 购买红包H5的加载地址正式地址
         */
//release
//    public static String RED_LOAD_URL = "https://mz.caiqr.com/jingcai/userCenter/redPurchase.html";
//debug
//    public static String RED_LOAD_URL = "https://mzlottery.caiqr.cn/jingcai/userCenter/redPurchase.html";

    }
}
