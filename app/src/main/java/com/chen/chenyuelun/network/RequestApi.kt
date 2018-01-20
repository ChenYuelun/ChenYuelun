package com.chen.chenyuelun.network

import com.chen.chenyuelun.network.response.GetBannerAdvertiseManagementResponse
import io.reactivex.Observable
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.HeaderMap
import retrofit2.http.POST


/**
 * Created by chenyuelun on 2018/1/20.
 */
interface RequestApi {
    //    //闪屏倒计时接口
    @FormUrlEncoded()
    @POST("api/get_banner_advertise_management")
    fun get_banner_advertise_management(@HeaderMap map: Map<String, String>, @FieldMap maps: Map<String, String>): Observable<GetBannerAdvertiseManagementResponse>
}