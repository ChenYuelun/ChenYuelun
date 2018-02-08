package com.chen.chenyuelun.presenter

import com.chen.chenyuelun.data.entity.HomeMenuItemBean
import com.chen.chenyuelun.model.MainModelmpl
import com.chen.chenyuelun.view.activity.MainActivity
import com.chen.libraryresouse.base.IModel
import com.chen.libraryresouse.base.IPrsenter
import com.chen.libraryresouse.base.IView

/**
 * Created by chenyuelun on 2018/2/8.
 */
class MainPresenter<T : MainActivity> : IPrsenter<T>() {
    val mModel = MainModelmpl()
    override fun fetch() {
        mModel.readNavigtionData(object : IModel.OnDataLoadListener<List<HomeMenuItemBean>> {
            override fun onComplete(data: List<HomeMenuItemBean>) {
                if (mViewRef.get() != null) {
                    mViewRef.get()!!.initNavigationAndFragments(data)
//                    mViewRef.get()!!.showData(data)
                }
            }
        })
    }


}