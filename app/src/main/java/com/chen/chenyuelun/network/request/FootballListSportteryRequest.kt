package com.chen.chenyuelun.network.request

import com.chen.chenyuelun.network.ParamsMapKey
import com.chen.chenyuelun.network.ParamsMapValue

/**
 * Created by chenyuelun on 2018/2/9.
 */
class FootballListSportteryRequest(val data :String) :BaseRequest(){
    init {
        params[ParamsMapKey.CMD] = ParamsMapValue.CMD_FOOTBALL_LIST_SPORTTERY
        params[ParamsMapKey.DATE] = data
    }
}