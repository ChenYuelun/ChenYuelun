package com.chen.libraryresouse.base.mvvm

import android.databinding.BaseObservable
import android.databinding.ViewDataBinding
import java.io.Serializable
import java.lang.ref.WeakReference

/**
 * Created by chenyuelun on 2018/2/9.
 */
abstract class BaseViewModel<T : ViewDataBinding> : BaseObservable(), Serializable {

    lateinit var mDataBinding:WeakReference<T>
    fun attch(t:T){
        mDataBinding = WeakReference(t)
    }


    fun detach(){
        mDataBinding.clear()
    }

    abstract fun feach()
}