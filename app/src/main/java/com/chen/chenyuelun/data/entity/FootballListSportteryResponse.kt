package com.chen.chenyuelun.data.entity

import android.databinding.Bindable
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by chenyuelun on 2018/2/9.
 */

data class FootballListSportteryResponse(
        @SerializedName("code") val code: Int,
        @SerializedName("msg") val msg: String,
        @SerializedName("resp") val resp: List<JcOrSfcMatchs>,
        @SerializedName("popup_info") val popupInfo: Any,
        @SerializedName("ad") val ad: String,
        @SerializedName("ad_img") val adImg: String
):Serializable

data class JcOrSfcMatchs(
        @SerializedName("未完场") val WeiWanChang: List<JcOrSfcMatchBean>,
        @SerializedName("已完场") val YiWanChang: List<JcOrSfcMatchBean>
):Serializable

data class JcOrSfcMatchBean(
        @SerializedName("match_id") val matchId: String,
        @SerializedName("match_week") val matchWeek: String,
        @SerializedName("match_sn") val matchSn: String,
        @SerializedName("is_hidden") val isHidden: Int,
        @SerializedName("is_danguan") val isDanguan: Int,
        @SerializedName("match_status") val matchStatus: Int,
        @SerializedName("match_status_desc") val matchStatusDesc: String,
        @SerializedName("host_id") val hostId: String,
        @SerializedName("away_id") val awayId: String,
        @SerializedName("season_id") val seasonId: Int,
        @SerializedName("match_desc") val matchDesc: String,
        @SerializedName("cup_index") val cupIndex: Int,
        @SerializedName("match_sort") val matchSort: Int,
        @SerializedName("host_score") val hostScore: String,
        @SerializedName("away_score") val awayScore: String,
        @SerializedName("host_half_score") val hostHalfScore: String,
        @SerializedName("away_half_score") val awayHalfScore: String,
        @SerializedName("host_rank") val hostRank: String,
        @SerializedName("away_rank") val awayRank: String,
        @SerializedName("season_pre") val seasonPre: String,
        @SerializedName("group_pre") val groupPre: String,
        @SerializedName("extra_info") val extraInfo: String,
        @SerializedName("spf_cnt") val spfCnt: String,
        @SerializedName("manual_sort") val manualSort: Int,
        @SerializedName("match_time") val matchTime: String,
        @SerializedName("caiqiu_index") val caiqiuIndex: String,
        @SerializedName("rqbackup") val rqbackup: Any,
        @SerializedName("rqcaiqiu_index") val rqcaiqiuIndex: Any,
        @SerializedName("recommend") val recommend: String,
        @SerializedName("is_hot") val isHot: String,
        @SerializedName("okooo_live") val okoooLive: String,
        @SerializedName("match_live_source") val matchLiveSource: String,
        @SerializedName("finish_time") val finishTime: Any,
        @SerializedName("home_show") val homeShow: Int,
        @SerializedName("bet_action") val betAction: BetAction,
        @SerializedName("league") val league: String,
        @SerializedName("season") val season: String,
        @SerializedName("bet_match_id") val betMatchId: String,
        @SerializedName("live_desc") val liveDesc: String,
        @SerializedName("forecast") val forecast: String,
        @SerializedName("has_rangqiu") val hasRangqiu: Int,
        @SerializedName("forecast_rangqiu") val forecastRangqiu: Any,
        @SerializedName("match_start_time") val matchStartTime: Any,
        @SerializedName("match_half_time") val matchHalfTime: Any,
        @SerializedName("sevenm_content") val sevenmContent: String,
        @SerializedName("sevenm_statis") val sevenmStatis: String,
        @SerializedName("sevenm_live") val sevenmLive: String,
        @SerializedName("tv_apk") val tvApk: TvApk,
        @SerializedName("live_desc_new") val liveDescNew: String,
        @SerializedName("match_status_desc_new") val matchStatusDescNew: String,
        @SerializedName("season_order") val seasonOrder: Int,
        @SerializedName("season_image") val seasonImage: Any,
        @SerializedName("league_id") val leagueId: Any,
        @SerializedName("host_team_image") val hostTeamImage: String,
        @SerializedName("away_team_image") val awayTeamImage: String,
        @SerializedName("host_name") val hostName: String,
        @SerializedName("away_name") val awayName: String,
        @SerializedName("odds") val odds: Odds,
        @SerializedName("max_return") val maxReturn: Boolean,
        @SerializedName("max_odds") val maxOdds: Double,
        @SerializedName("avge_odds") val avgeOdds: Double,
        @SerializedName("benefit") val benefit: String,
        @SerializedName("match_details") val matchDetails: List<Any>,
        @SerializedName("match_statis") val matchStatis: MatchStatis
):Serializable

data class Odds(
        @SerializedName("spf") val spf: List<Double>,
        @SerializedName("rqspf") val rqspf: List<Double>,
        @SerializedName("bifen") val bifen: List<Double>
):Serializable

data class TvApk(
        @SerializedName("cntv") val cntv: String
):Serializable

data class BetAction(
        @SerializedName("type") val type: String,
        @SerializedName("sport_ctl_match_id") val sportCtlMatchId: String
):Serializable

data class MatchStatis(
        @SerializedName("host_formation") val hostFormation: String,
        @SerializedName("away_formation") val awayFormation: String,
//        @SerializedName("host_players") val hostPlayers: List<List<HostPlayer>>,
//        @SerializedName("away_players") val awayPlayers: List<List<AwayPlayer>>,
        @SerializedName("host_static_shotnum") val hostStaticShotnum: Int,
        @SerializedName("away_static_shotnum") val awayStaticShotnum: Int,
        @SerializedName("host_static_shotznum") val hostStaticShotznum: Int,
        @SerializedName("away_static_shotznum") val awayStaticShotznum: Int,
        @SerializedName("host_static_pass") val hostStaticPass: Int,
        @SerializedName("away_static_pass") val awayStaticPass: Int,
        @SerializedName("host_static_steal") val hostStaticSteal: Int,
        @SerializedName("away_static_steal") val awayStaticSteal: Int,
        @SerializedName("host_static_foul") val hostStaticFoul: Int,
        @SerializedName("away_static_foul") val awayStaticFoul: Int,
        @SerializedName("host_static_corner") val hostStaticCorner: Int,
        @SerializedName("away_static_corner") val awayStaticCorner: Int,
        @SerializedName("host_static_offside") val hostStaticOffside: Int,
        @SerializedName("away_static_offside") val awayStaticOffside: Int,
        @SerializedName("host_static_red") val hostStaticRed: Int,
        @SerializedName("away_static_red") val awayStaticRed: Int,
        @SerializedName("host_static_yellow") val hostStaticYellow: Int,
        @SerializedName("away_static_yellow") val awayStaticYellow: Int,
        @SerializedName("host_static_controltime") val hostStaticControltime: String,
        @SerializedName("away_static_controltime") val awayStaticControltime: String,
        @SerializedName("host_static_attack") val hostStaticAttack: Int,
        @SerializedName("away_static_attack") val awayStaticAttack: Int,
        @SerializedName("host_static_risk_attack") val hostStaticRiskAttack: Int,
        @SerializedName("away_static_risk_attack") val awayStaticRiskAttack: Int,
        @SerializedName("host_static_free_kick") val hostStaticFreeKick: Int,
        @SerializedName("away_static_free_kick") val awayStaticFreeKick: Int
):Serializable