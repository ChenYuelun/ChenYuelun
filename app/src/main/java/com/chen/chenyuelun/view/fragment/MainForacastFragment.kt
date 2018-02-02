package com.chen.chenyuelun.view.fragment

import android.view.View
import com.chen.chenyuelun.R
import com.chen.chenyuelun.presenter.MainForecastPresentet
import com.chen.chenyuelun.view.BaseView
import com.chen.libraryresouse.base.BaseFragment
import kotlinx.android.synthetic.main.layout_fragment_main_foracast.*

/**
 * Created by chenyuelun on 2018/2/2.
 */
class MainForacastFragment : BaseFragment(),BaseView {

    val presenter = MainForecastPresentet(this)

    override fun getLayoutId() = R.layout.layout_fragment_main_foracast

    override fun getTitleLyout()= titleLayout

    override fun setUp() {
        super.setUp()
    }

    override fun requestApi() {
        presenter.requestDataFromApi()
    }


    override fun onError(hasData: Boolean) {
    }

    override fun onSeccuess() {
    }

}