package com.chen.chenyuelun.data.single

import com.chen.chenyuelun.data.entity.HomeCatalogBean
import com.chen.libraryresouse.utils.CacheManager

/**
 * Created by chenyuelun on 2018/1/31.
 */
class AppInfo private constructor() {
    var cache: CacheManager? = null
    var homeCatalog: MutableList<HomeCatalogBean>? = null
        get() {
            if (field != null) {
                field!!.sortBy({ it.order.dec() })
            }
            return field
        }

    companion object {
        val instance = AppInfo()
    }

    fun getCacheManager(): CacheManager {
        if (cache == null) {
            cache = CacheManager[AppApplication.instance()]
        }
        return cache!!
    }
}