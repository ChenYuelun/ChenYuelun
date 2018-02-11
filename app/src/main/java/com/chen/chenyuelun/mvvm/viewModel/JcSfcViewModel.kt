package com.chen.chenyuelun.mvvm.viewModel

import android.databinding.Bindable
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import com.chen.chenyuelun.data.entity.FootballListSportteryResponse
import com.chen.chenyuelun.data.entity.JcOrSfcMatchBean
import com.chen.chenyuelun.databinding.FragmentCalenderJcBinding
import com.chen.chenyuelun.mvvm.model.JcSfcModel
import com.chen.libraryresouse.base.mvvm.BaseViewModel
import com.chen.libraryresouse.base.mvp.IModel
import kotlinx.android.synthetic.main.refresh_layout.view.*

/**
 * Created by chenyuelun on 2018/2/9.
 */
class JcSfcViewModel : BaseViewModel<FragmentCalenderJcBinding>() {

    val mModel = JcSfcModel()
    //展示的已完场还是未完场  true 未完场  false已完场
    var status: ObservableField<Boolean> = ObservableField(true)

    val datas_yiwangchang = ObservableField(mutableListOf<JcOrSfcMatchBean>())
    val datas_weiwanchang = ObservableField(mutableListOf<JcOrSfcMatchBean>())
    var datas = ObservableField(mutableListOf<JcOrSfcMatchBean>())
    //    val datas = ObservableField(ObservableArrayList<JcOrSfcMatchBean>())
    override fun feach() {

        mModel.loadNetwork(object : IModel.OnDataLoadListener<FootballListSportteryResponse> {
            override fun onComplete(data: FootballListSportteryResponse) {
//                datas.set(data.resp[0].WeiWanChang.toMutableList())
                if (data.resp[0].WeiWanChang != null && data.resp[0].WeiWanChang.isNotEmpty())
                    datas_weiwanchang.get().addAll(data.resp[0].WeiWanChang)
                if (data.resp[0].YiWanChang != null && data.resp[0].YiWanChang.isNotEmpty())
                    datas_yiwangchang.get().addAll(data.resp[0].YiWanChang)
                onTabChanged()
            }
        })
    }

    fun onTabChanged() {
        if (status.get()) {
            datas.get().clear()
            datas.get().addAll(datas_weiwanchang.get())
        } else {
            datas.get().clear()
            datas.get().addAll(datas_yiwangchang.get())
        }
        mDataBinding.get()!!.refreshLayout!!.recyclerView.adapter.notifyDataSetChanged()
    }


}

