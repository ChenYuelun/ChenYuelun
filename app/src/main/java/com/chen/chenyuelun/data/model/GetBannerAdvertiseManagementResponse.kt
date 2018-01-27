package com.chen.chenyuelun.data.model

/**
 * Created by chenyuelun on 2018/1/20.
 */

data class GetBannerAdvertiseManagementResponse(override val code: Int,
                                                override val msg: String,
                                                override val resp: List<Any>,
                                                override val ad: String,
                                                val url: String, //eaglecp://eagleNativewap&url=baidu.com
                                                val time: String, //567
                                                val type: Int, //1
                                                val show_url: String //http://7vzspj.com2.z0.glb.qiniucdn.com/o_1bgqfa1jhlsu1e2c1qs6eh1i3t9.jpg


) : BaseResponse

