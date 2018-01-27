package com.chen.chenyuelun.data.model

/**
 * Created by chenyuelun on 2018/1/22.
 */

data class GetNotBetChannelAndVersion(override val code: Int,
                           override val msg: String,
                           override val resp: List<Any>,
                           override val ad: String,
                           val version_type: String, //caiqr
                           val version_name: String, //v3.5
                           val system_type: Int, //0
                           val bet_status: String, //０
                           val channel_id_status: Int //1
                           ) : BaseResponse



