package com.chen.chenyuelun.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by chenyuelun on 2018/2/3.
 */

data class MarqueeResponse(
        @SerializedName("code") val code: Int, //0
        @SerializedName("msg") val msg: String,
        @SerializedName("resp") val resp: List<MarqueeList>,
        @SerializedName("popup_info") val popupInfo: Any, //null
        @SerializedName("ad") val ad: String //文案待定
) : Serializable

data class MarqueeList(
        @SerializedName("title") val title: String, //跑马灯
        @SerializedName("content_list") val contentList: List<MarqueeBean>,
        @SerializedName("image") val image: String, //https://ojhwnbiut.qnssl.com/image/home/pmd_0001.png
        @SerializedName("order") val order: Int //3
) : Serializable

data class MarqueeBean(
        @SerializedName("news_id") val newsId: Int, //40227
        @SerializedName("group") val group: String, //app_broadcast
        @SerializedName("title") val title: String, //足球周一009比赛的预测结果发生了变化
        @SerializedName("content") val content: String, //拉斯帕尔马斯[主]vs马拉加,推荐主胜和主负
        @SerializedName("type") val type: String, //forecast
        @SerializedName("info") val info: String //caiqrgames://caiqrNative?page=zqyuce&contentid=687669
) : Serializable