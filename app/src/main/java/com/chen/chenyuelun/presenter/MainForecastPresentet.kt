package com.chen.chenyuelun.presenter

import com.chen.chenyuelun.data.model.HomeCatalogBean
import com.chen.chenyuelun.data.model.HomeCatalogResponse
import com.chen.chenyuelun.data.network.ParamsMapValue
import com.chen.chenyuelun.data.single.AppInfo
import com.chen.chenyuelun.view.BaseView

/**
 * Created by chenyuelun on 2018/2/2.
 */
class MainForecastPresentet(view :BaseView) :BasePresenter{

    override fun readDataFromCache() {
        if (AppInfo.instance.homeCatalog ==null){
            val catalogCache = AppInfo.instance.getCacheManager().getAsAny(ParamsMapValue.CMD_HOME_CATALOG)
            if (catalogCache != null && catalogCache is HomeCatalogResponse){
                AppInfo.instance.homeCatalog = catalogCache.resp.toMutableList()
            }else{
                defaultCatalogData()
            }

        }

    }

    private fun defaultCatalogData() {
        AppInfo.instance.homeCatalog = mutableListOf()
        AppInfo.instance.homeCatalog!!.add(HomeCatalogBean("caiqiu_focus_04",1,"焦点图",""))
        AppInfo.instance.homeCatalog!!.add(HomeCatalogBean("home_top_navigate",2,"首页上部导航",""))
        AppInfo.instance.homeCatalog!!.add(HomeCatalogBean("home_instant_message",3,"跑马灯",""))
        AppInfo.instance.homeCatalog!!.add(HomeCatalogBean("home_result_statistics",4,"预测战绩",""))
        AppInfo.instance.homeCatalog!!.add(HomeCatalogBean("home_guess_you_like",5,"猜你喜欢",""))
        AppInfo.instance.homeCatalog!!.add(HomeCatalogBean("home_guess_you_like",6,"首页赛事",""))
    }

    override fun requestDataFromApi() {
    }


}