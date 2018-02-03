package com.chen.chenyuelun.data.network.request

import com.chen.chenyuelun.data.network.ParamsMapKey
import com.chen.chenyuelun.data.network.ParamsMapValue
import com.chen.chenyuelun.data.single.UserInfo

/**
 * Created by chenyuelun on 2018/2/3.
 */
class MarqueeRequest :BaseRequest(){
    init {
        params[ParamsMapKey.CMD] = ParamsMapValue.CMD_HOME_INSTANT_MESSAGE
        if (UserInfo.getInstance().isLogin) {
            params[ParamsMapKey.TOKEN] = UserInfo.getInstance().token
        }
    }
}