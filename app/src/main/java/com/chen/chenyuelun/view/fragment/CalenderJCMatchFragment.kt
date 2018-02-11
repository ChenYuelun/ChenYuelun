package com.chen.chenyuelun.view.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chen.chenyuelun.BR
import com.chen.chenyuelun.databinding.FragmentCalenderJcBinding
import com.chen.libraryresouse.base.mvvm.BaseFragment2
import com.chen.chenyuelun.mvvm.adapter.JcRvAdapter
import com.chen.chenyuelun.mvvm.viewModel.JcSfcViewModel
import kotlinx.android.synthetic.main.refresh_layout.*

/**
 * Created by chenyuelun on 2018/2/9.
 */
class CalenderJCMatchFragment : BaseFragment2<FragmentCalenderJcBinding, JcSfcViewModel>() {

    override fun createViewModel(): JcSfcViewModel {
        return JcSfcViewModel()
    }

    override fun getBanding(inflater: LayoutInflater, container: ViewGroup?): FragmentCalenderJcBinding{
        return FragmentCalenderJcBinding.inflate(inflater,container!!,false)
    }

    override fun getTitleLyout() = null

//    override fun getLayoutId() = R.layout.fragment_calender_jc

    override fun setUp() {
        super.setUp()
        mDataBinding.setVariable(BR.viewModel,viewModel)
        recyclerView.adapter = JcRvAdapter(viewModel.datas)
        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        viewModel.feach()
        mDataBinding.matchStatus!!.setOnSelectStatus {

            if (it == mDataBinding.matchStatus!!.tvWeiwanchang){
                if(viewModel.status.get()) return@setOnSelectStatus
                viewModel.status.set(true)
                viewModel.onTabChanged()
            }else if (it == mDataBinding.matchStatus!!.tvYiwanchang){
                if(!viewModel.status.get()) return@setOnSelectStatus
                viewModel.status.set(false)
                viewModel.onTabChanged()
            }
        }
    }




}