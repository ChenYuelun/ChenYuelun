package com.chen.chenyuelun.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chen.chenyuelun.R
import com.chen.chenyuelun.data.entity.SeasonList
import com.chen.libraryresouse.utils.DensityUtils
import com.chen.libraryresouse.utils.toast
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
        val bean = data.seasons[position]
        com.chen.libraryresouse.utils.ImageLoader.loadImage(bean.seasonImage,holder!!.itemView.iv_match_logo)
        holder.itemView.tv_match_name.text = bean.seasonName
        val drawable = GradientDrawable()
        drawable.cornerRadius = DensityUtils.dip2px(context,5f).toFloat()
        if (!TextUtils.isEmpty(bean.color)) {
            drawable.setColor(Color.parseColor("#" + bean.color))
        } else {
            drawable.setColor(Color.parseColor("#eeeeee"))
        }
        holder.itemView.ll_bg.setBackgroundDrawable(drawable)
        if (position == itemCount - 1) {
            //item之间的间距  写死在布局里
            holder.itemView.view_width.setVisibility(View.VISIBLE)
        } else {
            holder.itemView.view_width.setVisibility(View.GONE)
        }

        holder.itemView.setOnClickListener {
            toast(bean.seasonName)
        }
    }

}