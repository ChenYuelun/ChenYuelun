package com.chen.chenyuelun.view.fragment

import com.chen.chenyuelun.R
import com.chen.chenyuelun.presenter.MainSocialPresenter
import com.chen.libraryresouse.base.mvp.BaseFragment
import com.chen.libraryresouse.base.mvp.IView

/**
 * Created by chenyuelun on 2018/2/2.
 */
class MainSocialFragment: BaseFragment<MainSocialFragment, MainSocialPresenter>(), IView {
    override fun showLoading() {

    }

    override fun showData(data: Any) {
    }

    override fun createPresenter(): MainSocialPresenter {
        return MainSocialPresenter()
    }

    override fun getTitleLyout()=null


    override fun getLayoutId()= R.layout.layout_fragment_main_social


}