package com.chen.chenyuelun.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.chen.chenyuelun.R
import com.chen.chenyuelun.data.entity.BasketballItem
import com.chen.chenyuelun.data.entity.FootballItem
import com.chen.libraryresouse.base.EnumForecastType
import com.chen.libraryresouse.utils.AnimationUtils
import com.chen.libraryresouse.utils.DateTools
import com.chen.libraryresouse.utils.ImageLoader
import com.chen.libraryresouse.utils.toast
import kotlinx.android.synthetic.main.layout_main_item_match.view.*
import kotlinx.android.synthetic.main.layout_main_match_tab.view.*
import kotlinx.android.synthetic.main.view_match_forecast.view.*

/**
 * Created by chenyuelun on 2018/2/6.
 */
class HomeMatchListRvAdapter(val context: Context, var footballMatchs: MutableList<FootballItem>, var basketballMatchs: MutableList<BasketballItem>) : RecyclerView.Adapter<MyViewHolder>() {

    var isShowFootball = true
    override fun getItemCount(): Int {
        var count = 0;
        if (isShowFootball) {
            if (footballMatchs.isNotEmpty()) {
                count = footballMatchs.size + 1
            } else {
                count = 2;
            }
        } else {
            if (basketballMatchs.isNotEmpty()) {
                count = footballMatchs.size + 1
            } else {
                count = 2;
            }
        }
        return count
    }

    override fun getItemViewType(position: Int): Int {
        var type = -1
        if (position == 0) {
            type = EnumForecastType.TYPE_NO_MATCHS.type
        } else {
            if ((isShowFootball && footballMatchs.isNotEmpty()) || (!isShowFootball && basketballMatchs.isNotEmpty())) {
                type = EnumForecastType.TYPE_MATCHS.type
            } else {
                type = EnumForecastType.TYPE_NO_MATCHS.type
            }
        }
        return type
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        var viewId = 0;
        when (viewType) {
            EnumForecastType.TYPE_NO_MATCHS.type -> {
                viewId = R.layout.layout_main_match_tab
            }
            EnumForecastType.TYPE_NO_MATCHS.type -> {
                viewId = R.layout.layout_main_no_match
            }
            EnumForecastType.TYPE_MATCHS.type -> {
                viewId = R.layout.layout_main_item_match
            }
        }
        return MyViewHolder(LayoutInflater.from(context).inflate(viewId, parent, false))
    }
    var tempPositionFootball = 0
    var tempPositionBasketball = 0

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        val viewType = getItemViewType(position)
        when (viewType) {
            EnumForecastType.TYPE_NO_MATCHS.type -> {
                if (isShowFootball) {
                    holder!!.itemView.tv_choice_football.setBackgroundResource(R.drawable.bg_recommend_choice_football)
                    holder.itemView.tv_choice_basketball.setBackgroundResource(R.drawable.bg_recommend_no_choice_basketball)
                    holder.itemView.tv_choice_football.setTextColor(Color.parseColor("#FFFFFF"))
                    holder.itemView.tv_choice_basketball.setTextColor(Color.parseColor("#5494FF"))
                } else {
                    holder!!.itemView.tv_choice_basketball.setBackgroundResource(R.drawable.bg_recommend_choice_basketball)
                    holder.itemView.tv_choice_football.setBackgroundResource(R.drawable.bg_recommend_no_choice_football)
                    holder.itemView.tv_choice_football.setTextColor(Color.parseColor("#5494FF"))
                    holder.itemView.tv_choice_basketball.setTextColor(Color.parseColor("#FFFFFF"))

                }
                holder.itemView.tv_choice_football.setOnClickListener {
                    isShowFootball = true
                    notifyDataSetChanged()
                }
                holder.itemView.tv_choice_basketball.setOnClickListener {

                    isShowFootball = false
                    notifyDataSetChanged()
                }
            }
            EnumForecastType.TYPE_NO_MATCHS.type -> {

            }
            EnumForecastType.TYPE_MATCHS.type -> {
                val itemView = holder!!.itemView
                val alphaAnimations = AnimationUtils.getAlphaAnimationList(1, 0, 1000, 3)
                val alpha_win = alphaAnimations.get(0)
                val alpha_ping = alphaAnimations.get(1)
                val alpha_lose = alphaAnimations.get(2)
                if (isShowFootball) {
                    val bean = footballMatchs[position - 1]
                    //设置联赛
                    if (!TextUtils.isEmpty(bean.matchWeek) && !TextUtils.isEmpty(bean.matchSn)) {

                        itemView.tv_bet_match_id.setVisibility(View.VISIBLE)
                        itemView.tv_bet_match_id.setText(bean.matchWeek + bean.matchSn)
                    } else {
                        itemView.tv_bet_match_id.setVisibility(View.GONE)
                    }
                    itemView.tv_league.setText(bean.seasonPre + bean.groupPre + ">>")
                    //联赛这一条的点击事件
                    itemView.ll_intentSeason.setOnClickListener(View.OnClickListener {
                        if (!TextUtils.isEmpty(bean.seasonId)) {
//                            val intent: Intent
//                            intent = Intent(ctx, SeasonMatch_Forecast_Activity::class.java)
//                            intent.putExtra("season_id", bean.seasonId)
//                            intent.putExtra("season_name", bean.seasonPre)
//                            context.startActivity(intent)
                            toast(bean.seasonPre)
                        }
                    })
                    //设置直播图标显示
                    //是否显示动画标志
                    if (!TextUtils.isEmpty(bean.okoooLive) && bean.okoooLive.contains("http")) {
                        itemView.ll_donghua.setVisibility(View.VISIBLE)
                        itemView.textView32.setText("动画直播")
                        itemView.imageView82.setImageResource(R.drawable.live_dong_blue)
                    } else {
                        itemView.ll_donghua.setVisibility(View.GONE)
                    }

                    //设置比赛开赛时间
                    itemView.tv_match_time.setText(DateTools.getFormatTimeforToday(bean.matchTime))
                    itemView.tv_match_time2.setText(DateTools.getFormatTime(bean.matchTime))

                    //球队图标
                    ImageLoader.loadImage(bean.hostTeamImage, itemView.iv_host_icon, R.drawable.default_team_icon, R.drawable.default_team_icon)
                    ImageLoader.loadImage(bean.awayTeamImage, itemView.iv_away_icon, R.drawable.default_team_icon, R.drawable.default_team_icon)

                    //球队名称
                    itemView.tv_host_name.text = bean.hostName
                    itemView.tv_away_name.text = bean.awayName
                    //[主]
                    itemView.tv_footballHost.visibility = View.VISIBLE
                    itemView.tv_basketballHost.visibility = View.GONE
                    //隐藏让球
                    itemView.tv_RqNumber.visibility = View.INVISIBLE
                    //根据 预测结果 3 1 0   3,1   3,0   1,0 判断显示的预测结果
                    val forecast = bean.forecast
                    judgeForecastStatus(forecast.contains("3"), itemView.ibtn_sheng, itemView.v_win, itemView.tv_sheng)
                    judgeForecastStatus(forecast.contains("1"), itemView.ibtn_ping, itemView.v_ping, itemView.tv_ping)
                    judgeForecastStatus(forecast.contains("0"), itemView.ibtn_fu, itemView.v_lose, itemView.tv_fu)

                    if (tempPositionFootball == position-1) {
                        //记录已播放过动画的位置
                        tempPositionFootball++
                        //可加载动画位置，执行透明度动画
                        if (itemView.v_win.getVisibility() == View.VISIBLE ) {
                            itemView.v_win.startAnimation(alpha_win)
                        }
                        if (itemView.v_ping.getVisibility() == View.VISIBLE ) {
                            itemView.v_ping.startAnimation(alpha_ping)
                        }
                        if (itemView.v_lose.getVisibility() == View.VISIBLE ) {
                            itemView.v_lose.startAnimation(alpha_lose)
                        }
                    } else {
                        //动画无效后，隐藏所有动画视图
                        itemView.v_win.setVisibility(View.GONE)
                        itemView.v_ping.setVisibility(View.GONE)
                        itemView.v_lose.setVisibility(View.GONE)
                    }


                    //胜负平 注意空指针
                    if (bean.odds != null && bean.odds.spf!= null) {
                        itemView.tv_sheng.text = "主胜${bean.odds.spf[0]}"
                        itemView.tv_ping.text = "平局${bean.odds.spf[1]}"
                        itemView.tv_fu.text = "主负${bean.odds.spf[2]}"
                        itemView.ll_forecastP.setVisibility(View.VISIBLE)
                    } else {
                        itemView.tv_sheng.text = "主胜"
                        itemView.tv_ping.text = "平局"
                        itemView.tv_fu.text = "主负"
                        itemView.ll_forecastP.visibility = View.VISIBLE
                    }
                    //信心指数
                    val confidenceIndex = Integer.parseInt(bean.caiqiuIndex)
                    if (confidenceIndex > 0) {
                        itemView.confidenceView.setVisibility(View.VISIBLE)
                        itemView.confidenceView.setProbability(confidenceIndex.toFloat())
                    } else {
                        itemView.confidenceView.setVisibility(View.INVISIBLE)
                    }

                    if (TextUtils.isEmpty(forecast)) {
                        itemView.confidenceView.visibility = View.GONE
                        itemView.tv_noForecast.visibility = View.VISIBLE
                    } else {
                        itemView.tv_noForecast.visibility = View.GONE
                    }

                    itemView.ib_collect.setOnClickListener {
                        toast("收藏")
                    }
                    //整个item的点击
                    itemView.ll_pinned_bottom.setOnClickListener {
                        toast("${bean.hostName} VS ${bean.awayName}")
                    }

                } else {
                    val bean = basketballMatchs[position-1]
                    //设置联赛
                    if (!TextUtils.isEmpty(bean.matchWeek) && !TextUtils.isEmpty(bean.matchSn)) {
                        itemView.tv_bet_match_id.visibility = View.VISIBLE
                        itemView.tv_bet_match_id.text = bean.matchWeek + bean.matchSn
                    } else {
                        itemView.tv_bet_match_id.setVisibility(View.GONE)
                    }
                    itemView.tv_league.text = bean.seasonPre + bean.groupPre
                    //联赛这一条的点击事件
                    itemView.ll_intentSeason.setOnClickListener(View.OnClickListener {
                        if (!TextUtils.isEmpty(bean.seasonId)) {
//                            val intent: Intent
//                            intent = Intent(ctx, SeasonMatch_Forecast_Activity::class.java)
//                            intent.putExtra("season_id", bean.seasonId)
//                            intent.putExtra("season_name", bean.seasonPre)
//                            context.startActivity(intent)
                            toast(bean.seasonPre)
                        }
                    })
                    //设置直播图标显示
                    //是否显示动画标志
                    itemView.ll_donghua.setVisibility(View.GONE)
                    //设置比赛开赛时间
                    itemView.tv_match_time.setText(DateTools.getFormatTimeforToday(bean.matchTime))
                    itemView.tv_match_time2.setText(DateTools.getFormatTime(bean.matchTime))

                    //球队图标
                    ImageLoader.loadImage(bean.hostTeamImage, itemView.iv_away_icon, R.drawable.default_team_icon, R.drawable.default_team_icon)
                    ImageLoader.loadImage(bean.awayTeamImage, itemView.iv_away_icon, R.drawable.default_team_icon, R.drawable.default_team_icon)

                    //球队名称
                    itemView.tv_host_name.text = bean.awayName
                    itemView.tv_away_name.text = bean.hostName
                    //[主]
                    itemView.tv_footballHost.visibility = View.GONE
                    itemView.tv_basketballHost.visibility = View.VISIBLE
                    //隐藏让球
                    itemView.tv_RqNumber.visibility = View.INVISIBLE

                    //根据 预测结果 3 1 0   3,1   3,0   1,0 判断显示的预测结果
                    val forecast = bean.forecast
                    judgeForecastStatus(forecast.contains("1"), itemView.ibtn_sheng, itemView.v_win, itemView.tv_sheng)
                    judgeForecastStatus(forecast.contains("2"), itemView.ibtn_fu, itemView.v_lose, itemView.tv_fu)

                    if (tempPositionBasketball == position-1) {
                        //记录已播放过动画的位置
                        tempPositionBasketball++
                        //可加载动画位置，执行透明度动画
                        if (itemView.v_win.getVisibility() == View.VISIBLE ) {
                            itemView.v_win.startAnimation(alpha_win)
                        }
                        if (itemView.v_lose.getVisibility() == View.VISIBLE ) {
                            itemView.v_lose.startAnimation(alpha_lose)
                        }
                    } else {
                        //动画无效后，隐藏所有动画视图
                        itemView.v_win.setVisibility(View.GONE)
                        itemView.v_lose.setVisibility(View.GONE)
                    }
                    //胜负平 注意空指针
                    if (bean.odds != null && bean.odds.sf!= null) {
                        itemView.tv_sheng.text = "主负" + bean.odds.sf[0]
                        itemView.tv_fu.text = "主胜" + bean.odds.sf[1]
                        itemView.ll_forecastP.visibility = View.GONE
                    } else {
                        itemView.tv_sheng.setText("主负")
                        itemView.tv_fu.setText("主胜")
                        itemView.ll_forecastP.setVisibility(View.GONE)
                    }
                    //信心指数
                    val confidenceIndex = Integer.parseInt(bean.caiqiuIndex)
                    if (confidenceIndex > 0) {
                        itemView.confidenceView.setVisibility(View.VISIBLE)
                        itemView.confidenceView.setProbability(confidenceIndex.toFloat())
                    } else {
                        itemView.confidenceView.setVisibility(View.INVISIBLE)
                    }

                    if (TextUtils.isEmpty(forecast)) {
                        itemView.confidenceView.visibility = View.GONE
                        itemView.tv_noForecast.visibility = View.VISIBLE
                    } else {
                        itemView.tv_noForecast.visibility = View.GONE
                    }

                    itemView.ib_collect.setOnClickListener {
                        toast("收藏")
                    }
                    //整个item的点击
                    itemView.ll_pinned_bottom.setOnClickListener {
                        toast("${bean.hostName} VS ${bean.awayName}")
                    }


                }
            }
        }
    }

    /**
     * 判断赔率推荐的的状态，设置是否选中，是否显示动画视图
     *
     * @param isChecked 是否推荐
     * @param iBtn      选中的按钮
     * @param view      动画的视图
     * @param textView
     */
    private fun judgeForecastStatus(isChecked: Boolean, iBtn: ImageButton, view: View, textView: TextView) {
        if (isChecked) {
            iBtn.setBackgroundResource(R.drawable.tuijian)
            view.visibility = View.VISIBLE
            textView.setTextColor(context.getResources().getColor(R.color.blue3))
        } else {
            iBtn.setBackgroundResource(R.drawable.weituijian)
            view.visibility = View.GONE
            textView.setTextColor(context.getResources().getColor(R.color.text333))
        }
    }

}