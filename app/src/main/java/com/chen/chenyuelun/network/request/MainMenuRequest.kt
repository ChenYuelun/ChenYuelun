package com.chen.chenyuelun.network.request

import com.chen.chenyuelun.network.ParamsMapKey
import com.chen.chenyuelun.network.ParamsMapValue

/**
 * Created by chenyuelun on 2018/1/31.
 */
class MainMenuRequest() : BaseRequest() {
    init {
        params[ParamsMapKey.CMD] = ParamsMapValue.CMD_HOME_DYNAMIC_MENU
        params["version"] = "2.0"
    }
}