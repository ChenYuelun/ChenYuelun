package com.chen.chenyuelun.data.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by chenyuelun on 2018/2/3.
 */

data class TopNavigationResponse(
		@SerializedName("code") val code: Int, //0
		@SerializedName("msg") val msg: String,
		@SerializedName("resp") val resp: List<TopNavigationList>,
		@SerializedName("popup_info") val popupInfo: Any, //null
		@SerializedName("ad") val ad: String //文案待定
):Serializable

data class TopNavigationList(
		@SerializedName("title") val title: String, //首页图标
		@SerializedName("icon_top_list") val iconTopList: List<TopNavigationItemBean>,
		@SerializedName("order") val order: Int //2
):Serializable

data class TopNavigationItemBean(
		@SerializedName("content") val content: String, //足球预测
		@SerializedName("forward_url") val forwardUrl: String,
		@SerializedName("img_url") val imgUrl: String, //http://7vzspj.com2.z0.glb.qiniucdn.com/image/home/top_navigate/%E8%B6%B3%E7%90%83%E9%A2%84%E6%B5%8B.png
		@SerializedName("dynamic_img_url") val dynamicImgUrl: String,
		@SerializedName("position_tag") val positionTag: String, //1
		@SerializedName("dynamic_bottom_url") val dynamicBottomUrl: String,
		@SerializedName("isDynamic_switch") val isDynamicSwitch: Int, //0
		@SerializedName("action") val action: Int, //1
		@SerializedName("img_url_no_white") val imgUrlNoWhite: Any, //null
		@SerializedName("white_content") val whiteContent: String,
		@SerializedName("client_info") val clientInfo: String, //0_5.2.0
		@SerializedName("client_type") val clientType: String, //0
		@SerializedName("version") val version: String, //5.2.0
		@SerializedName("push") val push: String, //caiqrgames://caiqrNative?page=zqyucelb
		@SerializedName("pushURL") val pushURL: String //caiqrgames://caiqrNative?page=zqyucelb
):Serializable