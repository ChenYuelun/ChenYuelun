package com.chen.chenyuelun.data.single
import com.chen.chenyuelun.data.model.HomeCatalogBean
import com.chen.libraryresouse.utils.CacheManager

/**
 * Created by chenyuelun on 2018/1/31.
 */
class AppInfo private constructor(){

    var homeCatalog : MutableList<HomeCatalogBean>? = null

    companion object {
        val instance = AppInfo()
    }

    fun getCacheManager() : CacheManager{
        return CacheManager[AppApplication.instance()]
    }
}