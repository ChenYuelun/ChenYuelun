package com.chen.chenyuelun.data.network.request

import com.chen.chenyuelun.data.single.UserInfo
import com.chen.chenyuelun.data.network.ParamsMapValue
import com.chen.librarynetwork.utils.Base64
import com.chen.librarynetwork.utils.Global
import com.chen.libraryresouse.utils.AppTools.Companion.getCurrentTimeMillis
import com.chen.libraryresouse.utils.AppTools.Companion.getTraceId
import com.chen.libraryresouse.utils.LogUtils

import java.security.KeyFactory
import java.security.spec.PKCS8EncodedKeySpec
import java.util.HashMap

/**
 * Created by chenyuelun on 2018/1/20.
 */



/**
 * Created by wanxin on 16/11/21.
 */

open class BaseRequest {

    private val SIGN_ALGORITHMS = "SHA1WithRSA"
    private val SIGN_SECRET = "MIIEowIBAAKCAQEAtHfdjyg0IU0YWdGvTQuelEZc9kVE2zwfc4e25fMTgz3EPwCR" +
            "Y39TnjaawWbmtUFTzxykjxmvg7nEbXntRoVeZ3Fy/LJG5jy/L8EnHxyOXS15dmoA" +
            "dKL138W3Ljpnju0J/qaFYxwx5iY5Nv8S9DyH+bVwyPupnEGY7ZuOPZOm2F8WVtXb" +
            "fT2R33TmqdTsA7FUiVB0ImrNDEYpMGHYzhDz/lURUxXWVgSMHu8NWpIHq13a7Q0r" +
            "3pGXfaaA8WVJU+T5fJz9Vh9D0W3qQo+rx9BLkDrMTrwmp04HLS3c382qIEDZY52Y" +
            "JtGYCWE6zGcyz/aGj+fUbFcTb/nMp11Kty9l/QIDAQABAoIBAGjMBmwAU61W4KIf" +
            "4qVh7vd5mLgl52NMGZN0JuCHdDhNIO3Y/ZSCtuL0II/YmnZ89hcaMfErYEGlGYzd" +
            "Nc0y22xEdBqLyCIg+oDXsEkfwwgWxSbfTV5t10vXu5Trs4FK4vfuaRzxnYRhg+4M" +
            "10ve7rMyCsmbYpXrfcvU/heB62xPaazeo6Gdw09sGyBfthBqHQFwlNyXAYKjmFwq" +
            "Th/aouWqUHL7lD5CVFOxIkWdU3T18Dv47tKCnzJT+AIOCJKtRphoQgb8W1J87sxZ" +
            "0tjewa8yUr1vRAAjraB3+EGCGBMxZKimZPr7TJtxhtbD3L/+gKctfWQW8RZOWg/r" +
            "xOYrBJ0CgYEA2/vWkzWAdsZgXLoe2F8AK8ydBShM8CCVpoXZOpxiEN0dMnvK3Df2" +
            "xcHhl5+fF4NNr+qz6tcfkuTxTtisuruFfMUW2nuaA8wLwApf3jWNi3VtnwbjjaFk" +
            "rdeHRjnQqsK7VPz8I5raW+ElgKUu1cilc+gMZ/S5lNrTPhQWXed5JO8CgYEA0gPQ" +
            "/gBwb1DO/nQ1i7VRwev1iQdQwAuL81WJY7sV3K1BHL0SYiJeWA0kc8EZlTPAaFgj" +
            "+QlRtGFdnisckO5sMTKhmTq14ea/hFDQ366K7LrzsdbzcrD7UVQo/Qh3L19JnBec" +
            "dbOCrTVsbwUobJvUBmGrUtdVq/dimPI0w5cbW9MCgYEAwE7sSkyfPTY2z1k11dlX" +
            "APMi4XtVpyYVLTodqQtE/5ENETD/Kn0SEddoK3CzV47Fv1/iYQ4m3/ecrFzmw2dj" +
            "TqvNSrp67Y7XRs7K+CrAzoWdi0QucYYByad87ntkW6NYuPdgRI//DAtLCaddxi5d" +
            "1XGqRsa95c9WlBJjazwLv6kCgYB4eIv4SJQ2pqo2uL7Gh5qAnTRaCII/x9/eFCEd" +
            "voIJ9rC1JZhYBtMLu24/oHNmbStgL79i6f+ec4PaaWyXjs0tY5fjaBRRo6YU5q8K" +
            "wPscVRnZ2C4Kio9/1cE1dRe5avW0vq6XrgO2DEFJZjIMK09y4a//c1rHrL84cSfn" +
            "UtT01QKBgEt7k7pSikzmcT3XD1DhOL3+4UBS2eEnpPriXWrBtsHt//CXFsFU1TI2" +
            "PMH+G6L+ubzO3+t78DafZrv92605jMsbt5Q9jGYRnoHd8cOK1ClaW/0r2yVhIJF5" +
            "m7Scz/Vkvcbh05dDV47aC6KZKp/6Z/LBEByeswxcOMZ1g7GxA5eW"


    protected var params: MutableMap<String, String> = HashMap()


    init {
        defaultDate()
    }

    fun defaultDate() {
        params.put("caiqr_version", ParamsMapValue.CAIQR_VERSION)
        params.put("caiqr_timestamp", getCurrentTimeMillis())
        params.put("trace_id", getTraceId())
        //TODO 登录之后要传token
        if (UserInfo.getInstance().isLogin) {
            params.put("token", UserInfo.getInstance().token)
        }
    }

    fun getRequestMap(): Map<String, String> {
        return params
    }

    fun getSign(): Map<String, String> {
        val map = HashMap<String, String>()
        val str = getSignEncodeStr(params)
        LogUtils.d("22222111111===>" + str)
        map["Caiqr-Signature"] = this.sign(str, SIGN_SECRET)!!
        return map
    }

    //获取要签名的串
    private fun getSignEncodeStr(map: Map<String, String>): String {
        val encodedParams = StringBuilder()

        try {
            for ((key, value) in map) {
                encodedParams.append(Global.encodeURIComponent(key))
                encodedParams.append('=')
                encodedParams.append(Global.encodeURIComponent(value))
                encodedParams.append('&')
            }

            val signOrginal = encodedParams.toString()
            return signOrginal.substring(0, signOrginal.length - 1)

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ""
    }

    //拼接要进行签名uri
    fun sign(content: String, privateKey: String): String? {
        val charset = "utf-8"
        try {
            val priPKCS8 = PKCS8EncodedKeySpec(
                    Base64.decode(privateKey))
            val keyf = KeyFactory.getInstance("RSA", "BC")
            val priKey = keyf.generatePrivate(priPKCS8)

            val signature = java.security.Signature
                    .getInstance(SIGN_ALGORITHMS)

            signature.initSign(priKey)
            signature.update(content.toByteArray(charset(charset)))

            val signed = signature.sign()
            return Base64.encode(signed)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }
}
