package com.chen.chenyuelun.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chen.chenyuelun.R
import com.chen.chenyuelun.data.model.SeasonList
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.item_guess_you_like.view.*

/**
 * Created by chenyuelun on 2018/2/5.
 */
class GuessYouLikeRvAdapter(val context: Context,var data : SeasonList) :RecyclerView.Adapter<MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_guess_you_like,parent,false))
    }

    override fun getItemCount()= if (data.seasons.isEmpty()) 0 else data.seasons.size

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        com.chen.libraryresouse.utils.ImageLoader.loadImage(data.seasons[position].seasonImage,holder!!.itemView.iv_match_logo)
        holder.itemView.tv_match_name.text = data.seasons[position].seasonName
    }

}