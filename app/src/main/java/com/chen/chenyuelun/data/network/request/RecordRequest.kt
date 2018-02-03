package com.chen.chenyuelun.data.network.request

import com.chen.chenyuelun.data.network.ParamsMapKey
import com.chen.chenyuelun.data.network.ParamsMapValue

/**
 * Created by chenyuelun on 2018/2/3.
 */
class RecordRequest:BaseRequest(){
    init {
        params[ParamsMapKey.CMD] =ParamsMapValue.CMD_HOME_RESULT
    }
}