package com.chen.chenyuelun.data.network.request

import android.text.TextUtils
import com.chen.chenyuelun.data.network.ParamsMapKey
import com.chen.chenyuelun.data.network.ParamsMapValue
import com.chen.chenyuelun.data.single.UserInfo
import com.chen.libraryresouse.utils.PhoneParameterUtils

/**
 * Created by chenyuelun on 2018/2/3.
 */
class TopNavigationRequest :BaseRequest(){
    init {
        params[ParamsMapKey.CMD] = ParamsMapValue.CMD_TOP_NAVIGATE
        if (UserInfo.getInstance().isLogin) {
            params[ParamsMapKey.TOKEN] = UserInfo.getInstance().token
        }
        if (PhoneParameterUtils.getChannelName().isNotEmpty()) {
            params[ParamsMapKey.CHANNEL_ID] = PhoneParameterUtils.getChannelName()
        }
    }
}