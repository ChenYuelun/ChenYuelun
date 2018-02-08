package com.chen.chenyuelun.data.entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by chenyuelun on 2018/2/3.
 */

data class GuessYouLikeResponse(
		@SerializedName("code") val code: Int, //0
		@SerializedName("msg") val msg: String,
		@SerializedName("resp") val resp: List<SeasonList>,
		@SerializedName("popup_info") val popupInfo: Any, //null
		@SerializedName("ad") val ad: String //文案待定
):Serializable

data class SeasonList(
		@SerializedName("title") val title: String, //猜你喜欢
		@SerializedName("seasons") val seasons: List<SeasonItemBean>,
		@SerializedName("order") val order: Int //9
): Serializable

data class SeasonItemBean(
		@SerializedName("season_id") val seasonId: Int, //4597
		@SerializedName("season_name") val seasonName: String, //阿甲
		@SerializedName("season_image") val seasonImage: String, //http://7vzspj.com2.z0.glb.clouddn.com/develop/football/season/7364.png
		@SerializedName("push") val push: String,
		@SerializedName("color") val color: String //f2faff
):Serializable