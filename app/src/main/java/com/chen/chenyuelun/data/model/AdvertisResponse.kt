package com.chen.chenyuelun.data.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by chenyuelun on 2018/2/3.
 */

data class AdvertisResponse(
		@SerializedName("code") val code: Int, //0
		@SerializedName("msg") val msg: String,
		@SerializedName("resp") val resp: List<AdvertisBean>,
		@SerializedName("popup_info") val popupInfo: Any, //null
		@SerializedName("ad") val ad: String //文案待定
): Serializable

data class AdvertisBean(
		@SerializedName("title") val title: String, //首页广告位
		@SerializedName("order") val order: Int, //4
		@SerializedName("img") val img: String, //http://7vzspj.com2.z0.glb.qiniucdn.com/o_1buvc6ja81o9l195s1r6a1acn19op9.png
		@SerializedName("push") val push: String //caiqrgames://caiqrNative?page=wapszc&url=https://mz.caiqr.com/
):Serializable