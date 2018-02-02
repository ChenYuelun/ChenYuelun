package com.chen.chenyuelun.data.network

import com.chen.chenyuelun.data.model.GetBannerAdvertiseManagementResponse
import com.chen.chenyuelun.data.model.HomeCatalogResponse
import com.chen.chenyuelun.data.model.HomeMenuResponse
import io.reactivex.Observable
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.HeaderMap
import retrofit2.http.POST


/**
 * Created by chenyuelun on 2018/1/20.
 */
interface RequestApi {
    //闪屏倒计时接口
    @FormUrlEncoded()
    @POST("api/home_dynamic_menu")
    fun getHomeMenu(@HeaderMap map: Map<String, String>, @FieldMap maps: Map<String, String>): Observable<HomeMenuResponse>


    //闪屏倒计时接口
    @FormUrlEncoded()
    @POST("api/get_banner_advertise_management")
    fun getBannerAdvertiseManagement(@HeaderMap map: Map<String, String>, @FieldMap maps: Map<String, String>): Observable<GetBannerAdvertiseManagementResponse>


    //首页目录列表
    @FormUrlEncoded
    @POST("api/home_catalog")
    fun getHomeCatalog(@HeaderMap map: Map<String, String>, @FieldMap maps: Map<String, String>): Observable<HomeCatalogResponse>

}