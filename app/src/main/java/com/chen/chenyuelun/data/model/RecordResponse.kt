package com.chen.chenyuelun.data.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by chenyuelun on 2018/2/3.
 */

data class RecordResponse(
		@SerializedName("code") val code: Int, //0
		@SerializedName("msg") val msg: String,
		@SerializedName("resp") val resp: List<RecordBean>,
		@SerializedName("popup_info") val popupInfo: Any, //null
		@SerializedName("ad") val ad: String //文案待定
): Serializable

data class RecordBean(
		@SerializedName("order") val order: Int, //8
		@SerializedName("push") val push: String,
		@SerializedName("background_image") val backgroundImage: String, //https://ojhwnbiut.qnssl.com/image/result_statis.png
		@SerializedName("left") val left: Left,
		@SerializedName("right") val right: Right
):Serializable

data class Left(
		@SerializedName("title") val title: String, //彩球大数据预测准确率
		@SerializedName("content") val content: String, //高达85%
		@SerializedName("color") val color: String //FF4500
):Serializable

data class Right(
		@SerializedName("color") val color: String, //FF4500
		@SerializedName("title") val title: String, //02月02日
		@SerializedName("content") val content: String, //彩球连续命中7场
		@SerializedName("content_01") val content_01: String //彩球连续命中7场
):Serializable