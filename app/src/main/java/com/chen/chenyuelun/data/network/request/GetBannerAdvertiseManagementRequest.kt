package com.chen.chenyuelun.data.network.request

import com.chen.chenyuelun.data.network.ParamsMapKey
import com.chen.chenyuelun.data.network.ParamsMapValue

/**
 * Created by chenyuelun on 2018/1/20.
 */
class GetBannerAdvertiseManagementRequest(channelId: String) : BaseRequest() {

    init {
        params[ParamsMapKey.CMD] = ParamsMapValue.CMD_GET_BANNER_ADVERTISE_MANAGEMENT
        params[ParamsMapKey.CHANNEL_ID] = channelId
    }
}
