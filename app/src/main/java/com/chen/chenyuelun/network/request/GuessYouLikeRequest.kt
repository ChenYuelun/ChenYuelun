package com.chen.chenyuelun.network.request

import com.chen.chenyuelun.network.ParamsMapKey
import com.chen.chenyuelun.network.ParamsMapValue

/**
 * Created by chenyuelun on 2018/2/3.
 */
class GuessYouLikeRequest : BaseRequest() {
    init {
        params[ParamsMapKey.CMD] = ParamsMapValue.CMD_HOME_GUESS_YOU_LIKE
    }
}