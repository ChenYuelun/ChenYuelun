package com.chen.chenyuelun.network

/**
 * Created by chenyuelun on 2018/1/20.
 */
class ParamsMapKey private constructor() {
    companion object {
        const val CMD = "cmd"
        //渠道id
        const val CHANNEL_ID = "channel_id"
        const val VERSION = "version"
        const val TOKEN = "token"
        //彩票类型id   圈子跟单,群组代码  足球200,篮球201
        const val LOTTERY_CODE = "lottery_code"
        // 首页比赛列表 最后一条比赛的id
        const val LATEST_ID = "latest_id"
        val SHORT = "short"
        val DATE = "date"
    }
}
