package com.chen.chenyuelun.network.request

import com.chen.chenyuelun.network.ParamsMapKey
import com.chen.chenyuelun.network.ParamsMapValue

/**
 * Created by chenyuelun on 2018/2/9.
 */
class FootballDateSportteryRequest :BaseRequest(){
    init {
        params[ParamsMapKey.CMD] = ParamsMapValue.CMD_FOOTBALL_DATE_SPORTTERY
        params[ParamsMapKey.SHORT] = ParamsMapValue.ONE
    }
}