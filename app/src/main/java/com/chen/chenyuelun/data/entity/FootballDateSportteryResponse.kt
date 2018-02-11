package com.chen.chenyuelun.data.entity
import com.google.gson.annotations.SerializedName


/**
 * Created by chenyuelun on 2018/2/9.
 */


data class FootballDateSportteryResponse(
		@SerializedName("code") val code: Int, //0
		@SerializedName("msg") val msg: String,
		@SerializedName("resp") val resp: List<FootballDateSportteryBean>,
		@SerializedName("popup_info") val popupInfo: Any, //null
		@SerializedName("ad") val ad: String //文案待定
)

data class FootballDateSportteryBean(
		@SerializedName("date") val date: String, //2017-12-08
		@SerializedName("count") val count: Int, //1
		@SerializedName("is_cursor") val isCursor: Boolean, //false
		@SerializedName("week") val week: String //五
)