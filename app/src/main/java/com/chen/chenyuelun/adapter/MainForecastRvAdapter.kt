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
import com.chen.libraryresouse.utils.toast
import kotlinx.android.synthetic.main.layout_main_advertising.view.*
import kotlinx.android.synthetic.main.layout_main_banner.view.*
import kotlinx.android.synthetic.main.layout_main_guess_you_like.view.*
import kotlinx.android.synthetic.main.layout_main_marquee.view.*
import kotlinx.android.synthetic.main.layout_main_match_list.view.*
import kotlinx.android.synthetic.main.layout_main_record.view.*
import kotlinx.android.synthetic.main.layout_main_top_navigation.view.*

/**
 * Created by chenyuelun on 2018/2/3.
 */
class MainForecastRvAdapter(val context: Context, val data: HomeForecastData) : RecyclerView.Adapter<MainForecastRvAdapter.ForecastViewHolder>() {

    var headerCount = 0

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
                EnumForecastType.TYPE_MATCH_LIST.tag -> type = EnumForecastType.TYPE_MATCH_LIST.type
            }
        }
        return type
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ForecastViewHolder {
        var viewId = 0;
        when (viewType) {
            EnumForecastType.TYPE_BANNER.type ->
                viewId = R.layout.layout_main_banner
            EnumForecastType.TYPE_TOPNAVIGATION.type ->
                viewId = R.layout.layout_main_top_navigation
            EnumForecastType.TYPE_MARQUEE.type ->
                viewId = R.layout.layout_main_marquee
            EnumForecastType.TYPE_ADVERTISING.type ->
                viewId = R.layout.layout_main_advertising
            EnumForecastType.TYPE_HOT_MATCH.type ->
                viewId = R.layout.layout_main_hot_matchs
            EnumForecastType.TYPE_FOOT_LIVE.type ->
                viewId = R.layout.layout_main_football_live
            EnumForecastType.TYPE_BASKET_LIVE.type ->
                viewId = R.layout.layout_main_basketball_live
            EnumForecastType.TYPE_RECORD.type ->
                viewId = R.layout.layout_main_record
            EnumForecastType.TYPE_GUESS_YOU_LIKE.type ->
                viewId = R.layout.layout_main_guess_you_like
            EnumForecastType.TYPE_MATCH_LIST.type ->
                viewId = R.layout.layout_main_match_list

        }
        return ForecastViewHolder(LayoutInflater.from(context).inflate(viewId, parent, false))
    }

    override fun getItemCount(): Int {
        return if (AppInfo.instance.homeCatalog == null) 0 else AppInfo.instance.homeCatalog!!.size
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
                holder.itemView.main_forecast_banner.setOnBannerListener {
                    toast("点击了第${it}个Banner")
                }
            }
            EnumForecastType.TYPE_TOPNAVIGATION.type -> {
                if (holder!!.itemView.rv_top_navigation.adapter == null){
                    holder.itemView.rv_top_navigation.adapter = TopNavigationRvAdapter(context, data.topNavigationList)
                    holder.itemView.rv_top_navigation.layoutManager = GridLayoutManager(context, data.topNavigationList.iconTopList.size)
                }else{
                    holder.itemView.rv_top_navigation.adapter.notifyDataSetChanged()
                }
            }
            EnumForecastType.TYPE_MARQUEE.type -> {
                val contentList = mutableListOf<String>()
                data.marqueeList.contentList.forEach {
                    contentList.add(it.content)
                }
                holder!!.itemView.marqueeText.setContent(contentList)
                ImageLoader.loadImage(data.marqueeList.image, holder.itemView.iv_marquee)
                holder.itemView.setOnClickListener{
                    toast(data.marqueeList.title)
                }
            }
            EnumForecastType.TYPE_ADVERTISING.type -> {
                ImageLoader.loadImage(data.advertisBean.img, holder!!.itemView.iv_advertis)
                holder.itemView.iv_advertis.setOnClickListener {
                    toast("点击了广告位")
                }
            }
            EnumForecastType.TYPE_HOT_MATCH.type -> {
            }
            EnumForecastType.TYPE_FOOT_LIVE.type -> {
            }
            EnumForecastType.TYPE_BASKET_LIVE.type -> {
            }
            EnumForecastType.TYPE_RECORD.type -> {
                if (!TextUtils.isEmpty(data.recordList.left.title))
                    holder!!.itemView.tv_left_top.text = data.recordList.left.title
                if (!TextUtils.isEmpty(data.recordList.left.color))
                    holder!!.itemView.tv_left_top.setTextColor(Color.parseColor("#${data.recordList.left.color}"))
                if (!TextUtils.isEmpty(data.recordList.left.content))
                    holder!!.itemView.tv_left_bottom.text = data.recordList.left.content
                if (!TextUtils.isEmpty(data.recordList.right.title))
                    holder!!.itemView.tv_right_top.text = data.recordList.right.title
                if (!TextUtils.isEmpty(data.recordList.right.color))
                    holder!!.itemView.tv_right_top.setTextColor(Color.parseColor("#${data.recordList.right.color}"))
                if (!TextUtils.isEmpty(data.recordList.right.content))
                    holder!!.itemView.tv_right_center.text = data.recordList.right.content
                if (!TextUtils.isEmpty(data.recordList.right.content_01))
                    holder!!.itemView.tv_left_bottom.text = data.recordList.right.content_01
                ImageLoader.loadImage(data.recordList.backgroundImage, holder!!.itemView.iv_record)
                holder.itemView.setOnClickListener {
                    toast("点击了战绩统计")
                }
            }
            EnumForecastType.TYPE_GUESS_YOU_LIKE.type -> {
                if (holder!!.itemView.rv_guess_you_like.adapter == null) {
                    holder.itemView.rv_guess_you_like.adapter = GuessYouLikeRvAdapter(context, data.guessYouLikeList)
                    holder.itemView.rv_guess_you_like.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                } else {
                    holder.itemView.rv_guess_you_like.adapter.notifyDataSetChanged()
                }
            }
            EnumForecastType.TYPE_MATCH_LIST.type -> {
                if (holder!!.itemView.rv_match_list.adapter == null) {
                    holder.itemView.rv_match_list.adapter = HomeMatchListRvAdapter(context, data.footballMatchs, data.basketballMatchs)
                    holder.itemView.rv_match_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                } else {
                    holder.itemView.rv_match_list.adapter.notifyDataSetChanged()
                }
            }
        }
    }

    class ForecastViewHolder(itemtView: View) : RecyclerView.ViewHolder(itemtView)

}