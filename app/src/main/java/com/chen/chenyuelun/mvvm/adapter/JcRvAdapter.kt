package com.chen.chenyuelun.mvvm.adapter

import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chen.chenyuelun.BR
import com.chen.chenyuelun.R
import com.chen.chenyuelun.data.entity.JcOrSfcMatchBean
import com.chen.chenyuelun.databinding.ItemRecyclerJcFragmentBinding

/**
 * Created by chenyuelun on 2018/2/9.
 */
class JcRvAdapter(var datas: ObservableField<ObservableArrayList<JcOrSfcMatchBean>>) : RecyclerView.Adapter<JcRvAdapter.MVVMViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MVVMViewHolder {
        val binding = DataBindingUtil
                .inflate<ItemRecyclerJcFragmentBinding>(
                        LayoutInflater.from(parent!!.context),
                        R.layout.item_recycler_jc_fragment,
                        parent,
                        false
                )

        return MVVMViewHolder(binding)
    }

    override fun getItemCount() = datas.get().size

    override fun onBindViewHolder(holder: MVVMViewHolder?, position: Int) {

        holder!!.binding.setVariable(BR.bean, datas.get()[position])
        holder.binding.executePendingBindings()

    }

    class MVVMViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

}