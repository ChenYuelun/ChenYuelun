package com.chen.libraryresouse.base

import java.lang.ref.WeakReference

/**
 * Created by chenyuelun on 2018/2/8.
 */
abstract class IPrsenter<T :IView> {

    lateinit var mViewRef: WeakReference<T>

    fun attach(view: T) {
        mViewRef = WeakReference<T>(view)
    }

    fun detach() {
        mViewRef.clear()
    }

    //执行UI逻辑
    abstract fun fetch()

}