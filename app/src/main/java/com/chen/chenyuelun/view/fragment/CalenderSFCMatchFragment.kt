package com.chen.chenyuelun.view.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chen.chenyuelun.R
import com.chen.chenyuelun.databinding.FragmentCalenderJcBinding
import com.chen.chenyuelun.mvvm.BaseFragment2
import com.chen.chenyuelun.mvvm.viewModel.JcSfcViewModel

/**
 * Created by chenyuelun on 2018/2/9.
 */
class CalenderSFCMatchFragment : BaseFragment2<FragmentCalenderJcBinding, JcSfcViewModel>(){
    override fun createViewModel(): JcSfcViewModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBanding(inflater: LayoutInflater, container: ViewGroup?): FragmentCalenderJcBinding {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTitleLyout()=null

//    override fun getLayoutId() = R.layout.fragment_calender_sfc

}