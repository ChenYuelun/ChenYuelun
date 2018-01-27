package com.chen.chenyuelun.data.model

/**
 * Created by chenyuelun on 2018/1/22.
 */
interface BaseResponse {
    val code: Int //0
    val msg: String
    val resp: List<Any>
    val ad: String //文案待定
}