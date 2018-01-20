package com.chen.chenyuelun

import android.text.TextUtils

/**
 * Created by chenyuelun on 2018/1/20.
 *
 * 单例 存放用户数据类
 */
class UserInfo private constructor() {

    companion object {
        fun getInstance(): UserInfo {
            return inner.instance
        }
    }

    private object inner {
        val instance = UserInfo()
    }
    var isLogin = false;
    //昵称
    var nikeName = ""

    var userId = ""

    var token = ""

    var deviceToken = ""
    //imei
    private var imei = ""
    //头像地址
    private var userHeadUrl = ""
    //手机号
    private var userPhoneNumber = ""
    //身份证号
    private var userCardCode = ""
        set(value) {
            if (value.isNotEmpty()) {
                if (value.length == 15)
                    (value[0] + "*************" + value[value.length - 1])
                 else
                    (value[0] + "****************" + value[value.length - 1])
            }
        }
    //真实姓名
    private var userRealName = ""
    //微信号
    private var wx_id = ""
    //是否设置过支付密码
    private var has_pay_pwd = false
    //是否设置了登陆密码
    private var has_pwd = false
    //账户余额
    private var account_balance = ""
    //红包余额
    private var red_balance = ""
    //小额免密支付开关 0为关，1为开
    private var free_pay_pwd_status: Int = 0
    //小额免密支付金额
    private var free_pay_pwd_quota: Int = 0

    //-----------------------
    //对否验证过通过支付密码
    private var payPwdIsValidated = false
    //用户渠道
    private var channel = "others"
    //是否是管理员
    var is_admin = false
    //是否是成年
    var is_adult = false
    //是否是第一次投注
    var has_bet = false
    //身份证正面
    var card_image_url_front = ""
    //身份证背面
    var card_image_url_back = ""
    //vip头像url集合
    var vip_pictures: List<String>? = null
    //用户头像集合
    var head_pictures: List<String>? = null
    //用户头像右边的hello word
    var user_hello_word = ""

    //三方登录信息
    var oauth_id = ""  //oauth_id
    var third_name = "" //nick_name
    var third_headUrl = "" //头像url
    var province = "" //省
    var city = "" //城市
    var country = "" //国家
    var gender = "" //性别
    //用户是否是白名单
    var isWhiteList = false

}