package com.chen.chenyuelun.mvvm.viewModel

import android.databinding.BaseObservable
import android.databinding.ViewDataBinding
import java.io.Serializable
import java.lang.ref.WeakReference

/**
 * Created by chenyuelun on 2018/2/9.
 */
abstract class BaseViewModel<T : ViewDataBinding> : BaseObservable(), Serializable {

    lateinit var dataBinding :WeakReference<T>
    fun attch(t:T){
        dataBinding = WeakReference(t)
    }


    fun detach(){
        dataBinding.clear()
    }

    abstract fun feach()
}