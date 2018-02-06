package com.chen.chenyuelun.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by chenyuelun on 2018/2/3.
 */


data class HomeMatchListResponse(
        @SerializedName("code") val code: Int, //0
        @SerializedName("msg") val msg: String,
        @SerializedName("resp") val resp: List<ForecastMatchs>,
        @SerializedName("popup_info") val popupInfo: Any, //null
        @SerializedName("ad") val ad: String //文案待定
) : Serializable

data class ForecastMatchs(
        @SerializedName("football") val football: List<FootballItem>,
        @SerializedName("basketball") val basketball: List<BasketballItem>,
        @SerializedName("order") val order: Int //10
) : Serializable

data class FootballItem(
        @SerializedName("match_id") val matchId: String, //673410
        @SerializedName("host_id") val hostId: String, //5315
        @SerializedName("away_id") val awayId: String, //495
        @SerializedName("host_name") val hostName: String, //海登海姆
        @SerializedName("away_name") val awayName: String, //圣保利
        @SerializedName("host_team_image") val hostTeamImage: String, //http://7vzspj.com2.z0.glb.clouddn.com/develop/football/5315.png
        @SerializedName("away_team_image") val awayTeamImage: String, //http://7vzspj.com2.z0.glb.clouddn.com/develop/football/495.png
        @SerializedName("match_week") val matchWeek: String, //周六
        @SerializedName("match_sn") val matchSn: String, //003
        @SerializedName("match_status") val matchStatus: Int, //0
        @SerializedName("match_status_desc") val matchStatusDesc: String, //未开赛
        @SerializedName("season_pre") val seasonPre: String, //德乙
        @SerializedName("group_pre") val groupPre: String, //第21轮
        @SerializedName("forecast") val forecast: String, //3,0
        @SerializedName("caiqiu_index") val caiqiuIndex: String, //84
        @SerializedName("odds") val odds: FootballOdds,
        @SerializedName("match_time") val matchTime: String, //2018-02-03 20:00:00 +0800
        @SerializedName("okooo_live") val okoooLive: String,
        @SerializedName("season_id") val seasonId: String //4482
) : Serializable

data class FootballOdds(
        @SerializedName("spf") val spf: List<Double>,
        @SerializedName("rqspf") val rqspf: List<Double>,
        @SerializedName("bifen") val bifen: List<Double>
) : Serializable

data class BasketballItem(
        @SerializedName("match_id") val matchId: Int, //125959
        @SerializedName("host_id") val hostId: String, //12
        @SerializedName("away_id") val awayId: String, //22
        @SerializedName("host_name") val hostName: String, //快船
        @SerializedName("away_name") val awayName: String, //公牛
        @SerializedName("host_team_image") val hostTeamImage: String, //http://liansai.500.com/static/soccerdata/images/BasketBallTeamPic/b24448def8a79f07b0d71ee63fe32fd1.png
        @SerializedName("away_team_image") val awayTeamImage: String, //http://liansai.500.com/static/soccerdata/images/BasketBallTeamPic/260a906129438a7ece93213346b0376c.png
        @SerializedName("match_week") val matchWeek: String, //周六
        @SerializedName("match_sn") val matchSn: String, //301
        @SerializedName("match_status") val matchStatus: Int, //1
        @SerializedName("match_status_desc") val matchStatusDesc: String, //未开始
        @SerializedName("season_pre") val seasonPre: String, //NBA
        @SerializedName("group_pre") val groupPre: String, //常规赛
        @SerializedName("forecast") val forecast: String, //2
        @SerializedName("caiqiu_index") val caiqiuIndex: String, //99
        @SerializedName("odds") val odds: BasketballOdds,
        @SerializedName("match_time") val matchTime: String, //2018-02-04 04:30:00 +0800
        @SerializedName("okooo_live") val okoooLive: Any, //null
        @SerializedName("season_id") val seasonId: String //418
) : Serializable

data class BasketballOdds(
        @SerializedName("sf") val sf: List<Double>,
        @SerializedName("rf") val rf: List<Double>,
        @SerializedName("dx") val dx: List<Double>
) : Serializable