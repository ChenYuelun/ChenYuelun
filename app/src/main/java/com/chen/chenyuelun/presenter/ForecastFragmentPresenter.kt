package com.chen.chenyuelun.presenter

import com.chen.chenyuelun.data.entity.HomeForecastData
import com.chen.chenyuelun.model.MainForecastModelmpl
import com.chen.chenyuelun.view.fragment.MainForacastFragment
import com.chen.libraryresouse.base.IModel
import com.chen.libraryresouse.base.IPrsenter
import java.util.*

/**
 * Created by chenyuelun on 2018/2/8.
 */
class ForecastFragmentPresenter<T> : IPrsenter<MainForacastFragment>() {

    val mModel = MainForecastModelmpl()

    override fun fetch() {
        mModel.loadCache(object : IModel.OnDataLoadListener<Any> {
            override fun onComplete(data: Any) {
                if (mViewRef.get()!= null)
                mViewRef.get()!!.showData(data)
            }

        })
        mModel.loadNetwork(object : IModel.OnDataLoadListener<Any> {
            override fun onComplete(data: Any) {
                if (mViewRef.get()!= null)
                    mViewRef.get()!!.showData(data)
            }

        })
    }

    fun refresh() {

    }

}