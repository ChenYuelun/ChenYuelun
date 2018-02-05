package com.chen.chenyuelun.data.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by chenyuelun on 2018/2/2.
 */

data class HomeCatalogResponse(
		@SerializedName("code") val code: Int, //0
		@SerializedName("msg") val msg: String,
		@SerializedName("resp") val resp: List<HomeCatalogBean>,
		@SerializedName("popup_info") val popupInfo: Any, //null
		@SerializedName("ad") val ad: String //文案待定
):Serializable

data class HomeCatalogBean(
		@SerializedName("url") val url: String, //caiqiu_focus_04
		@SerializedName("order") val order: Int, //1
		@SerializedName("desc") val desc: String, //焦点图
		@SerializedName("backup") val backup: String //null
):Serializable