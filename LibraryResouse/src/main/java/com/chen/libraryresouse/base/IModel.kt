package com.chen.libraryresouse.base

/**
 * Created by chenyuelun on 2018/2/8.
 */
interface IModel<out T :Any> {

    fun loadCache(listener: OnDataLoadListener<T>)

    fun loadNetwork(listener: OnDataLoadListener<T>)


    interface OnDataLoadListener<in T : Any> {
        fun onComplete(data: T)
    }

}