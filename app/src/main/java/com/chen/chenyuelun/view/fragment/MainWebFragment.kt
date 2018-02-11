package com.chen.chenyuelun.view.fragment

import com.chen.chenyuelun.R
import com.chen.chenyuelun.presenter.MainWebPresenter
import com.chen.libraryresouse.base.mvp.BaseFragment
import com.chen.libraryresouse.base.mvp.IView

/**
 * Created by chenyuelun on 2018/2/2.
 */
class MainWebFragment : BaseFragment<MainWebFragment, MainWebPresenter>() , IView {
    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showData(data: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createPresenter() = MainWebPresenter()

    override fun getTitleLyout() = null

    override fun getLayoutId() = R.layout.layout_fragment_main_web
}