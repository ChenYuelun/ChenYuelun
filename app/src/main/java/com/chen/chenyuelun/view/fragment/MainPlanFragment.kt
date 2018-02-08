package com.chen.chenyuelun.view.fragment

import android.view.View
import com.chen.chenyuelun.R
import com.chen.chenyuelun.presenter.MainPlanPresenter
import com.chen.libraryresouse.base.BaseFragment

/**
 * Created by chenyuelun on 2018/2/2.
 */
class MainPlanFragment: BaseFragment<MainPlanFragment,MainPlanPresenter>(){
    override fun createPresenter(): MainPlanPresenter {
        return MainPlanPresenter()
    }

    override fun getTitleLyout()=null

    override fun getLayoutId()= R.layout.layout_fragment_main_plan

}