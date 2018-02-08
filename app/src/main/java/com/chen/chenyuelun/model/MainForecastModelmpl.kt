package com.chen.chenyuelun.model

import com.chen.chenyuelun.data.entity.HomeForecastData
import com.chen.libraryresouse.base.IModel

/**
 * Created by chenyuelun on 2018/2/8.
 */
class MainForecastModelmpl :IModel<HomeForecastData>{
    override fun loadCache(listener: IModel.OnDataLoadListener<HomeForecastData>) {

    }

    override fun loadNetwork(listener: IModel.OnDataLoadListener<HomeForecastData>) {
    }

}