package com.chen.chenyuelun.network.request

import com.chen.chenyuelun.network.ParamsMapKey
import com.chen.chenyuelun.network.ParamsMapValue

/**
 * Created by chenyuelun on 2018/2/3.
 */
class CaiqiuFocusRequest : BaseRequest(){
    init {
        params[ParamsMapKey.CMD] = ParamsMapValue.CMD_CAIQIU_FOCUS
    }
}