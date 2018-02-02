package com.chen.chenyuelun.data.network.request

import android.content.Context
import com.chen.chenyuelun.data.network.ParamsMapKey
import com.chen.chenyuelun.data.network.ParamsMapValue
import com.chen.chenyuelun.data.single.AppApplication
import com.chen.libraryresouse.utils.PhoneParameterUtils

/**
 * Created by chenyuelun on 2018/2/2.
 */
class HomeCatalogrequest : BaseRequest() {
    init {
        params[ParamsMapKey.CMD] = ParamsMapValue.CMD_HOME_CATALOG
        if (PhoneParameterUtils.getAppVersionCode(AppApplication.instance()).isNotEmpty()) {
            params[ParamsMapKey.VERSION] = PhoneParameterUtils.getAppVersionCode(AppApplication.instance())
        }
    }
}