package com.chen.chenyuelun.network.response

/**
 * Created by chenyuelun on 2018/1/20.
 */

data class GetBannerAdvertiseManagementResponse(
		val code: Int, //0
		val msg: String,
		val resp: List<Resp>,
		val popup_info: Any, //null
		val ad: String //文案待定
)

data class Resp(
		val url: String, //eaglecp://eagleNativewap&url=baidu.com
		val time: String, //567
		val type: Int, //1
		val show_url: String //http://7vzspj.com2.z0.glb.qiniucdn.com/o_1bgqfa1jhlsu1e2c1qs6eh1i3t9.jpg
)