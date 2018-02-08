package com.chen.chenyuelun.network

import com.chen.chenyuelun.data.entity.*
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

    //首页焦点图 banner
    @FormUrlEncoded
    @POST("api/caiqiu_focus_04")
    fun getCaiqiuFocus(@HeaderMap map: Map<String, String>, @FieldMap maps: Map<String, String>): Observable<CaiqiuFocusResponse>

    //首页上部四个导航菜单
    @FormUrlEncoded
    @POST("api/home_top_navigate")
    fun getTopNavigate(@HeaderMap map: Map<String, String>, @FieldMap maps: Map<String, String>): Observable<TopNavigationResponse>

    //首页跑马的数据请求
    @FormUrlEncoded
    @POST("api/home_instant_message")
    fun getMarquee(@HeaderMap map: Map<String, String>, @FieldMap maps: Map<String, String>): Observable<MarqueeResponse>

    //首页热门比赛
    @FormUrlEncoded
    @POST("api/home_hot_match")
    fun getHotBetting(@HeaderMap map: Map<String, String>, @FieldMap maps: Map<String, String>): Observable<HotBettingResponse>

    //首页战绩统计
    @FormUrlEncoded
    @POST("api/home_result_statistics")
    fun getRecord(@HeaderMap map: Map<String, String>, @FieldMap maps: Map<String, String>): Observable<RecordResponse>

    //首页猜你喜欢
    @FormUrlEncoded
    @POST("api/home_guess_you_like")
    fun getGuessYouLike(@HeaderMap map: Map<String, String>, @FieldMap maps: Map<String, String>): Observable<GuessYouLikeResponse>


    //首页广告位
    @FormUrlEncoded
    @POST("api/home_advertis")
    fun getHomeAdvertis(@HeaderMap map: Map<String, String>, @FieldMap maps: Map<String, String>): Observable<AdvertisResponse>


    //首页比赛预测列表 足球篮球
    @FormUrlEncoded
    @POST("api/home_match_list")
    abstract fun getHomeMatchList(@HeaderMap map: Map<String, String>, @FieldMap maps: Map<String, String>): Observable<HomeMatchListResponse>

}