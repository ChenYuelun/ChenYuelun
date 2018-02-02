package com.chen.chenyuelun.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.chen.chenyuelun.R
import com.chen.chenyuelun.data.model.HomeMenuItemBean
import com.chen.chenyuelun.view.activity.MainActivity
import com.chen.libraryresouse.base.MainTag
import com.chen.libraryresouse.utils.DensityUtils
import kotlinx.android.synthetic.main.item_main_menu.view.*

/**
 * Created by chenyuelun on 2018/1/31.
 */
class HomeMenuRvAdapter(private val context: MainActivity, private val data: List<HomeMenuItemBean>) : RecyclerView.Adapter<HomeMenuRvAdapter.HomeMenuViewHolder>() {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: HomeMenuViewHolder?, position: Int) {
        val itemData = data[position]
        holder!!.itemView.tv_mainMenu_item.text = itemData.text
        if (itemData.isSelected) {
            holder.itemView.iv_mainMenu_item.setImageBitmap(itemData.pressBitmap)
            holder.itemView.tv_mainMenu_item.setTextColor(itemData.pressColor)
        } else {
            holder.itemView.iv_mainMenu_item.setImageBitmap(itemData.normalBitmap)
            holder.itemView.tv_mainMenu_item.setTextColor(itemData.normalColor)
        }

        if (itemData.postionTag == MainTag.WEB.tag) {
            val params = holder.itemView.iv_mainMenu_item.layoutParams as RelativeLayout.LayoutParams
            params.height = DensityUtils.dip2px(context, 80f)
            params.width = DensityUtils.dip2px(context, 100f)
            params.topMargin = DensityUtils.dip2px(context, -30f)
            holder.itemView.tv_mainMenu_item.visibility = View.GONE
        } else {
            val params = holder.itemView.iv_mainMenu_item.layoutParams as RelativeLayout.LayoutParams
            params.height = DensityUtils.dip2px(context, 32f)
            params.width = DensityUtils.dip2px(context, 32f)
            params.topMargin = DensityUtils.dip2px(context, 0f)
            holder.itemView.tv_mainMenu_item.visibility = View.VISIBLE
        }
        holder.itemView.iv_mainMenu_item.requestLayout()
        holder.itemView.setOnClickListener {

            if (!itemData.isSelected) {
                data.forEach { it.isSelected = false }
                itemData.isSelected = true
                notifyDataSetChanged()
                context.changeFragmentShow(itemData.postionTag)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HomeMenuViewHolder {
        return HomeMenuViewHolder(View.inflate(parent!!.context, R.layout.item_main_menu, null))
    }

    fun changSlectItem(tag: String) {
        data.forEach {
            if (it.postionTag == tag && !it.isSelected) {
                data.forEach { it.isSelected = false }
                it.isSelected = true
                notifyDataSetChanged()
                context.changeFragmentShow(it.postionTag)
            }
        }
    }

    class HomeMenuViewHolder(view: View) : RecyclerView.ViewHolder(view)

}