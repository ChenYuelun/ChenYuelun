package com.chen.chenyuelun.network.request

import com.chen.chenyuelun.network.ParamsMapKey
import com.chen.chenyuelun.network.ParamsMapValue
import com.chen.chenyuelun.data.single.UserInfo

/**
 * Created by chenyuelun on 2018/2/3.
 */
class HotBetingRequest : BaseRequest(){
    init {
        params[ParamsMapKey.CMD] = ParamsMapValue.CMD_HOME_HOT_MATCH

        if (UserInfo.getInstance().isLogin) {
            params[ParamsMapKey.TOKEN] = UserInfo.getInstance().token
        }
    }
}