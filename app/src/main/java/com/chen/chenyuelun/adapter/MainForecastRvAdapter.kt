package com.chen.chenyuelun.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chen.chenyuelun.R
import com.chen.chenyuelun.data.model.HomeForecastData
import com.chen.chenyuelun.data.single.AppInfo
import com.chen.libraryresouse.base.EnumForecastType
import com.chen.libraryresouse.utils.GlideImageLoader
import com.chen.libraryresouse.utils.ImageLoader
import kotlinx.android.synthetic.main.layout_main_banner.view.*
import kotlinx.android.synthetic.main.layout_main_guess_you_like.view.*
import kotlinx.android.synthetic.main.layout_main_record.view.*
import kotlinx.android.synthetic.main.layout_main_top_navigation.view.*

/**
 * Created by chenyuelun on 2018/2/3.
 */
class MainForecastRvAdapter(val context: Context, val data: HomeForecastData) : RecyclerView.Adapter<MainForecastRvAdapter.ForecastViewHolder>() {

    var headerCount = 0
    var isShwoFoot = true


    fun setSelectTab(isShwoFoot: Boolean) {
        this.isShwoFoot = isShwoFoot
        notifyDataSetChanged()
    }

    fun refreshData() {

    }

    override fun getItemViewType(position: Int): Int {
        var type = -1
        val hearder = AppInfo.instance.homeCatalog
        if (position < hearder!!.size) {
            when (hearder[position].url) {
                EnumForecastType.TYPE_BANNER.tag -> type = EnumForecastType.TYPE_BANNER.type
                EnumForecastType.TYPE_TOPNAVIGATION.tag -> type = EnumForecastType.TYPE_TOPNAVIGATION.type
                EnumForecastType.TYPE_MARQUEE.tag -> type = EnumForecastType.TYPE_MARQUEE.type
                EnumForecastType.TYPE_ADVERTISING.tag -> type = EnumForecastType.TYPE_ADVERTISING.type
                EnumForecastType.TYPE_HOT_MATCH.tag -> type = EnumForecastType.TYPE_HOT_MATCH.type
                EnumForecastType.TYPE_FOOT_LIVE.tag -> type = EnumForecastType.TYPE_FOOT_LIVE.type
                EnumForecastType.TYPE_BASKET_LIVE.tag -> type = EnumForecastType.TYPE_BASKET_LIVE.type
                EnumForecastType.TYPE_RECORD.tag -> type = EnumForecastType.TYPE_RECORD.type
                EnumForecastType.TYPE_GUESS_YOU_LIKE.tag -> type = EnumForecastType.TYPE_GUESS_YOU_LIKE.type
                EnumForecastType.TYPE_MATCH_TAB.tag -> type = EnumForecastType.TYPE_MATCH_TAB.type
            }
        } else {
            if ((isShwoFoot && data.footballMatchs.isNotEmpty()) || (!isShwoFoot && data.basketballMatchs.isNotEmpty())) {
                type = EnumForecastType.TYPE_MATCHS.type
            } else {
                type = EnumForecastType.TYPE_NO_MATCHS.type
            }
        }
        return type
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ForecastViewHolder {
        var view: View? = null
        when (viewType) {
            EnumForecastType.TYPE_BANNER.type -> {
                view = LayoutInflater.from(context).inflate(R.layout.layout_main_banner, parent, false)
            }
            EnumForecastType.TYPE_TOPNAVIGATION.type -> {
                view = LayoutInflater.from(context).inflate(R.layout.layout_main_top_navigation, parent, false)
            }
            EnumForecastType.TYPE_MARQUEE.type -> {
                view = LayoutInflater.from(context).inflate(R.layout.layout_main_marquee, parent, false)
            }
            EnumForecastType.TYPE_ADVERTISING.type -> {
                view = LayoutInflater.from(context).inflate(R.layout.layout_main_advertising, parent, false)
            }
            EnumForecastType.TYPE_HOT_MATCH.type -> {
                view = LayoutInflater.from(context).inflate(R.layout.layout_main_hot_matchs, parent, false)
            }
            EnumForecastType.TYPE_FOOT_LIVE.type -> {
                view = LayoutInflater.from(context).inflate(R.layout.layout_main_football_live, parent, false)
            }
            EnumForecastType.TYPE_BASKET_LIVE.type -> {
                view = LayoutInflater.from(context).inflate(R.layout.layout_main_basketball_live, parent, false)
            }
            EnumForecastType.TYPE_RECORD.type -> {
                view = LayoutInflater.from(context).inflate(R.layout.layout_main_record, parent, false)
            }
            EnumForecastType.TYPE_GUESS_YOU_LIKE.type -> {
                view = LayoutInflater.from(context).inflate(R.layout.layout_main_guess_you_like, parent, false)
            }
            EnumForecastType.TYPE_MATCH_TAB.type -> {
                view = LayoutInflater.from(context).inflate(R.layout.layout_main_match_tab, parent, false)
            }
            EnumForecastType.TYPE_NO_MATCHS.type -> {
                view = LayoutInflater.from(context).inflate(R.layout.layout_main_no_match, parent, false)
            }
            EnumForecastType.TYPE_MATCHS.type -> {
                view = LayoutInflater.from(context).inflate(R.layout.layout_main_item_match, parent, false)
            }

        }
        return ForecastViewHolder(view!!)
    }

    override fun getItemCount(): Int {
        var count = 0
        headerCount = AppInfo.instance.homeCatalog!!.size
        if (isShwoFoot) {
            if (data.footballMatchs.isNotEmpty()) {
                count = headerCount + data.footballMatchs.size
            } else {
                count = headerCount + 1
            }
        } else {
            if (data.basketballMatchs.isNotEmpty()) {
                count = headerCount + data.basketballMatchs.size
            } else {
                count = headerCount + 1
            }
        }

        return count

    }


    override fun onBindViewHolder(holder: ForecastViewHolder?, position: Int) {
        val viewType = getItemViewType(position)
        when (viewType) {
            EnumForecastType.TYPE_BANNER.type -> {
                holder!!.itemView.main_forecast_banner.setImageLoader(GlideImageLoader())
                val images = mutableListOf<String>()
                data.caiqiuFocusList.focus.forEach {
                    images.add(it.image)
                }
                holder.itemView.main_forecast_banner.setImages(images)
                holder.itemView.main_forecast_banner.start()
            }
            EnumForecastType.TYPE_TOPNAVIGATION.type -> {
                holder!!.itemView.rv_top_navigation.adapter = TopNavigationRvAdapter(context, data.topNavigationList)
                holder.itemView.rv_top_navigation.layoutManager = GridLayoutManager(context, data.topNavigationList.iconTopList.size)
            }
            EnumForecastType.TYPE_MARQUEE.type -> {
            }
            EnumForecastType.TYPE_ADVERTISING.type -> {
            }
            EnumForecastType.TYPE_HOT_MATCH.type -> {
            }
            EnumForecastType.TYPE_FOOT_LIVE.type -> {
            }
            EnumForecastType.TYPE_BASKET_LIVE.type -> {
            }
            EnumForecastType.TYPE_RECORD.type -> {
                if (TextUtils.isEmpty(data.recordList.left.title))
                    holder!!.itemView.tv_left_top.text = data.recordList.left.title
                if (TextUtils.isEmpty(data.recordList.left.color))
                    holder!!.itemView.tv_left_top.setTextColor(Color.parseColor("#${data.recordList.left.color}"))
                if (TextUtils.isEmpty(data.recordList.left.content))
                    holder!!.itemView.tv_left_bottom.text = data.recordList.left.content
                if (TextUtils.isEmpty(data.recordList.right.title))
                    holder!!.itemView.tv_right_top.text = data.recordList.right.title
                if (TextUtils.isEmpty(data.recordList.right.color))
                    holder!!.itemView.tv_right_top.setTextColor(Color.parseColor("#${data.recordList.right.color}"))
                if (TextUtils.isEmpty(data.recordList.right.content))
                    holder!!.itemView.tv_right_center.text = data.recordList.right.content
                if (TextUtils.isEmpty(data.recordList.right.content_01))
                    holder!!.itemView.tv_left_bottom.text = data.recordList.right.content_01
                ImageLoader.loadImage(data.recordList.backgroundImage,holder!!.itemView.iv_record)
            }
            EnumForecastType.TYPE_GUESS_YOU_LIKE.type -> {
                holder!!.itemView.rv_guess_you_like.adapter =GuessYouLikeRvAdapter(context,data.guessYouLikeList)
                holder.itemView.rv_guess_you_like.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            }
            EnumForecastType.TYPE_MATCH_TAB.type -> {
            }
            EnumForecastType.TYPE_NO_MATCHS.type -> {
            }
            EnumForecastType.TYPE_MATCHS.type -> {
            }

        }
    }

    class ForecastViewHolder(itemtView: View) : RecyclerView.ViewHolder(itemtView)

}