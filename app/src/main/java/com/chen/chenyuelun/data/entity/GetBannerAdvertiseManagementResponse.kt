package com.chen.chenyuelun.data.entity
import com.google.gson.annotations.SerializedName


/**
 * Created by chenyuelun on 2018/1/20.
 */


data class GetBannerAdvertiseManagementResponse(
		@SerializedName("code") val code: Int, //0
		@SerializedName("msg") val msg: String,
		@SerializedName("resp") val resp: List<BannerAdvertiseResp>,
		@SerializedName("popup_info") val popupInfo: Any, //null
		@SerializedName("ad") val ad: String //文案待定
)

data class BannerAdvertiseResp(
		@SerializedName("url") val url: String, //eaglecp://eagleNativewap&url=baidu.com
		@SerializedName("time") val time: String, //567
		@SerializedName("type") val type: Int, //1
		@SerializedName("show_url") val showUrl: String //http://7vzspj.com2.z0.glb.qiniucdn.com/o_1bgqfa1jhlsu1e2c1qs6eh1i3t9.jpg
)

