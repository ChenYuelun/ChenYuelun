package com.chen.chenyuelun.data.entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by chenyuelun on 2018/1/31.
 */

data class HomeMenuResponse(
		@SerializedName("code") val code: Int, //0
		@SerializedName("msg") val msg: String,
		@SerializedName("resp") val resp: List<HomeMenuResp>,
		@SerializedName("popup_info") val popupInfo: Any, //null
		@SerializedName("ad") val ad: String //文案待定
): Serializable

data class HomeMenuResp(
		@SerializedName("text_list") val textList: List<HomeMenuText>,
		@SerializedName("able_type") val ableType: String, //1
		@SerializedName("able_version") val ableVersion: String, //100
		@SerializedName("normal_color") val normalColor: String, //#BCBBBB
		@SerializedName("press_color") val pressColor: String //#5494ff
): Serializable

data class HomeMenuText(
		@SerializedName("text") val text: String, //比赛预测
		@SerializedName("normal_url") val normalUrl: String, //http://7xoiug.com1.z0.glb.clouddn.com/bisaiyucehui.png
		@SerializedName("press_url") val pressUrl: String, //http://7xoiug.com1.z0.glb.clouddn.com/bisaiyuce.png
		@SerializedName("position_tag") val positionTag: String, //1
		@SerializedName("jump_url") val jumpUrl: String
): Serializable
