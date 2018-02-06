package com.chen.chenyuelun.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chen.chenyuelun.R
import com.chen.chenyuelun.data.model.TopNavigationList
import com.chen.libraryresouse.utils.ImageLoader
import com.chen.libraryresouse.utils.toast
import kotlinx.android.synthetic.main.item_top_navigation.view.*

/**
 * Created by chenyuelun on 2018/2/5.
 */
class TopNavigationRvAdapter(val context: Context,var data : TopNavigationList) :RecyclerView.Adapter<MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_top_navigation,null))
    }

    override fun getItemCount(): Int = if (data.iconTopList.isEmpty()) 0 else data.iconTopList.size

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        val bean =data.iconTopList[position]
        holder!!.itemView.tv_top_menu.text = bean.content
        ImageLoader.loadImage(bean.imgUrl,holder.itemView.iv_top_menu)
        holder.itemView.setOnClickListener{
            toast(bean.content)
        }
    }

}