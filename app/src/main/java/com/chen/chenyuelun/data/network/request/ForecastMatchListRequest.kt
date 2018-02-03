package com.chen.chenyuelun.data.network.request

import com.chen.chenyuelun.data.network.ParamsMapKey
import com.chen.chenyuelun.data.network.ParamsMapValue

/**
 * Created by chenyuelun on 2018/2/3.
 */
class ForecastMatchListRequest(lotteryCode: String, latestId: String) : BaseRequest() {
    init {
        params[ParamsMapKey.CMD] = ParamsMapValue.CMD_HOME_MATCH_LIST
        if (lotteryCode != "0"){
            params[ParamsMapKey.LOTTERY_CODE] = lotteryCode
            if (latestId != "0"){
                params[ParamsMapKey.LATEST_ID] = latestId
            }
        }
    }
}