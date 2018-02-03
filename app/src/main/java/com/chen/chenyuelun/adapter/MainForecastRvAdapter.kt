package com.chen.chenyuelun.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.chen.chenyuelun.data.single.AppInfo
import com.chen.libraryresouse.base.EnumForecastType

/**
 * Created by chenyuelun on 2018/2/3.
 */
class MainForecastRvAdapter(val context: Context, val data: MutableList<Any>) : RecyclerView.Adapter<MainForecastRvAdapter.ForecastViewHolder>() {

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
            type = if (data.isEmpty()) {
                EnumForecastType.TYPE_NO_MATCHS.type
            } else {
                EnumForecastType.TYPE_MATCHS.type
            }

        }
        return type
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ForecastViewHolder {
        var view: View? = null
        when (viewType) {
            EnumForecastType.TYPE_BANNER.type -> {
            }
            EnumForecastType.TYPE_TOPNAVIGATION.type -> {
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
            }
            EnumForecastType.TYPE_GUESS_YOU_LIKE.type -> {
            }
            EnumForecastType.TYPE_MATCH_TAB.type -> {
            }
            EnumForecastType.TYPE_NO_MATCHS.type -> {
            }
            EnumForecastType.TYPE_MATCHS.type -> {
            }

        }
        return ForecastViewHolder(view!!)
    }

    override fun getItemCount(): Int {
        headerCount = AppInfo.instance.homeCatalog!!.size
        return if (data.isNotEmpty()) headerCount + data.size else headerCount + 1

    }


    override fun onBindViewHolder(holder: ForecastViewHolder?, position: Int) {
        val viewType = getItemViewType(position)
        when (viewType) {
            EnumForecastType.TYPE_BANNER.type -> {
            }
            EnumForecastType.TYPE_TOPNAVIGATION.type -> {
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
            }
            EnumForecastType.TYPE_GUESS_YOU_LIKE.type -> {
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