package com.chen.chenyuelun.data.entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by chenyuelun on 2018/2/3.
 */

data class HotBettingResponse(
		@SerializedName("code") val code: Int, //0
		@SerializedName("msg") val msg: String,
		@SerializedName("resp") val resp: List<HotMatchs>,
		@SerializedName("popup_info") val popupInfo: Any, //null
		@SerializedName("ad") val ad: String //文案待定
) :Serializable

data class HotMatchs(
		@SerializedName("order") val order: Int, //5
		@SerializedName("type") val type: Int, //1
		@SerializedName("title") val title: String, //快速投注-单关
		@SerializedName("content") val content: String, //猜对一场就中奖
		@SerializedName("image") val image: String, //猜对一场就中奖
		@SerializedName("content_football") val contentFootball: List<FootballItemBean>,
		@SerializedName("content_basketball_list") val contentBasketballList: List<Any>
):Serializable

data class FootballItemBean(
		@SerializedName("match_id") val matchId: Int, //687665
		@SerializedName("match_week") val matchWeek: String, //周六
		@SerializedName("match_sn") val matchSn: String, //006
		@SerializedName("season_pre") val seasonPre: String, //西甲
		@SerializedName("host_id") val hostId: Int, //562
		@SerializedName("away_id") val awayId: Int, //464
		@SerializedName("host_rank") val hostRank: String, //[08]
		@SerializedName("away_rank") val awayRank: String, //[06]
		@SerializedName("host_name") val hostName: String, //埃瓦尔
		@SerializedName("away_name") val awayName: String, //塞维利亚
		@SerializedName("match_time") val matchTime: String, //2018-02-03 20:00:00 +0800
		@SerializedName("forecast") val forecast: String, //3,0
		@SerializedName("caiqiu_index") val caiqiuIndex: Int, //77
		@SerializedName("host_team_image") val hostTeamImage: String, //http://7vzspj.com2.z0.glb.clouddn.com/develop/football/562.png
		@SerializedName("away_team_image") val awayTeamImage: String, //http://static.sporttery.com/images/2013/01/22/23_1300424883.png
		@SerializedName("match_status_desc") val matchStatusDesc: Any, //null
		@SerializedName("sport_ctl_match_id") val sportCtlMatchId: String, //20180203006
		@SerializedName("match_issue") val matchIssue: String, //20180203006
		@SerializedName("spf") val spf: Spf,
		@SerializedName("issue_time") val issueTime: String, //今天19:45截止
		@SerializedName("multiple") val multiple: Int //5
):Serializable

data class Spf(
		@SerializedName("is_danguan") val isDanguan: Int, //1
		@SerializedName("play_type") val playType: String, //001
		@SerializedName("sp") val sp: List<String>,
		@SerializedName("change") val change: List<String>
)