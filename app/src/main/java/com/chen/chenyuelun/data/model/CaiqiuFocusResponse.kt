package com.chen.chenyuelun.data.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by chenyuelun on 2018/2/3.
 */

data class CaiqiuFocusResponse(
		@SerializedName("code") val code: Int, //0
		@SerializedName("msg") val msg: String,
		@SerializedName("resp") val resp: List<CaiqiuFocusList>,
		@SerializedName("popup_info") val popupInfo: Any, //null
		@SerializedName("ad") val ad: String //文案待定
):Serializable

data class CaiqiuFocusList(
		@SerializedName("order") val order: Int, //1
		@SerializedName("focus") val focus: List<CaiqiuFocusBean>
):Serializable

data class CaiqiuFocusBean(
		@SerializedName("action") val action: String, //wap
		@SerializedName("title") val title: String,
		@SerializedName("title2") val title2: String,
		@SerializedName("image") val image: String, //https://ohduoklem.qnssl.com/iphonex01.png
		@SerializedName("image01") val image01: String, //https://ohduoklem.qnssl.com/iphonex01.png
		@SerializedName("image02") val image02: String, //https://ohduoklem.qnssl.com/iphonex01.png
		@SerializedName("image03") val image03: String,
		@SerializedName("imageX") val imageX: String, //https://ohduoklem.qnssl.com/iphonex.png
		@SerializedName("desc") val desc: String,
		@SerializedName("channel_name") val channelName: Any, //null
		@SerializedName("channel_id") val channelId: String,
		@SerializedName("bet_status") val betStatus: Int, //0
		@SerializedName("push") val push: String, //caiqrgames://caiqrNative?page=wap&url=https://m.caiqr.cn/activity/dateAddLotteryDetail/index.html?calendar_date=2018-01-04&is_login=0&share_url=https://m.caiqr.cn/activity/dateAddLotteryDetail/index.html?calendar_date=2018-01-04&share_title=&share_describe=
		@SerializedName("share_url") val shareUrl: String, //https://m.caiqr.cn/activity/dateAddLotteryDetail/index.html?calendar_date=2018-01-04
		@SerializedName("share_icon") val shareIcon: String, //http://7vzspj.com1.z0.glb.clouddn.com/caiqr_icon.png
		@SerializedName("focus_sort") val focusSort: Int, //9999
		@SerializedName("football_focus_type") val footballFocusType: Int, //3
		@SerializedName("client_type") val clientType: Int, //2
		@SerializedName("params") val params: Params
):Serializable

data class Params(
		@SerializedName("is_login") val isLogin: Int, //0
		@SerializedName("url") val url: String, //https://m.caiqr.cn/activity/dateAddLotteryDetail/index.html?calendar_date=2018-01-04
		@SerializedName("share_url") val shareUrl: String //https://m.caiqr.cn/activity/dateAddLotteryDetail/index.html?calendar_date=2018-01-04
):Serializable