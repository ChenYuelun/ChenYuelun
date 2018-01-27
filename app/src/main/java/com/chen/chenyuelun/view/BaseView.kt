package com.chen.chenyuelun.view

/**
 * Created by chenyuelun on 2018/1/27.
 */
interface BaseView{

    fun showLoading(show :Boolean){}

    fun showNoData(show :Boolean){}

    fun showNoNet(show :Boolean){}

    fun onError(hasData :Boolean)

    fun onSeccuess()
}