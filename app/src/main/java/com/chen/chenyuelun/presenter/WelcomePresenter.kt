package com.chen.chenyuelun.presenter

import com.chen.chenyuelun.model.WelcomeModelmpI
import com.chen.libraryresouse.base.mvp.IModel
import com.chen.libraryresouse.base.mvp.IPrsenter
import com.chen.libraryresouse.base.mvp.IView

/**
 * Created by chenyuelun on 2018/1/27.
 */
class WelcomePresenter<T : IView> : IPrsenter<T>() {

    val mModel = WelcomeModelmpI()

    override fun fetch() {
        mModel.loadCache(object : IModel.OnDataLoadListener<String>{
            override fun onComplete(data: String) {
                if (mViewRef.get()!= null){
                    mViewRef.get()!!.showData(data)
                }

            }

        })
        mModel.loadNetwork(object : IModel.OnDataLoadListener<String>{
            override fun onComplete(data: String) {
                if (mViewRef.get()!= null){
                    mViewRef.get()!!.showData(data)
                }

            }

        })

    }

//
//    override fun requestDataFromApi() {

//    }
//



}