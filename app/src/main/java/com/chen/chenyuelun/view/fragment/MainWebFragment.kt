package com.chen.chenyuelun.view.fragment

import android.view.View
import com.chen.chenyuelun.R
import com.chen.chenyuelun.presenter.MainWebPresenter
import com.chen.libraryresouse.base.BaseFragment

/**
 * Created by chenyuelun on 2018/2/2.
 */
class MainWebFragment : BaseFragment<MainWebFragment, MainWebPresenter>() {

    override fun createPresenter() = MainWebPresenter()

    override fun getTitleLyout() = null

    override fun getLayoutId() = R.layout.layout_fragment_main_web
}