package com.chen.chenyuelun.mvvm.viewModel

import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import com.chen.chenyuelun.data.entity.FootballListSportteryResponse
import com.chen.chenyuelun.data.entity.JcOrSfcMatchBean
import com.chen.chenyuelun.databinding.FragmentCalenderJcBinding
import com.chen.chenyuelun.mvvm.adapter.JcRvAdapter
import com.chen.chenyuelun.mvvm.model.JcSfcModel
import com.chen.libraryresouse.base.IModel
import java.io.Serializable

/**
 * Created by chenyuelun on 2018/2/9.
 */
class JcSfcViewModel :BaseViewModel<FragmentCalenderJcBinding>(){

    val mModel = JcSfcModel()

//    val datas = ObservableField(mutableListOf<JcOrSfcMatchBean>())
    val datas = ObservableField(ObservableArrayList<JcOrSfcMatchBean>())
    override fun feach() {

        mModel.loadNetwork(object  : IModel.OnDataLoadListener<FootballListSportteryResponse>{
            override fun onComplete(data: FootballListSportteryResponse) {
                    datas.get().addAll(data.resp[0].WeiWanChang)
//                    dataBinding.get()!!.recyclerView.adapter.notifyDataSetChanged()
            }
        })
    }




}

