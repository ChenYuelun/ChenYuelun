package com.chen.chenyuelun.network.request

import com.chen.chenyuelun.network.ParamsMapKey
import com.chen.chenyuelun.network.ParamsMapValue

/**
 * Created by chenyuelun on 2018/1/20.
 */
class GetBannerAdvertiseManagementRequest(channelId: String) : BaseRequest() {

    init {
        params[ParamsMapKey.CMD] = ParamsMapValue.CMD_GET_BANNER_ADVERTISE_MANAGEMENT
        params[ParamsMapKey.CHANNEL_ID] = channelId
    }
}
