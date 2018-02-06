package com.chen.libraryresouse.utils

import android.annotation.SuppressLint
import android.text.TextUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by chenyuelun on 2018/2/6.
 */
class DateTools {

    companion object {
        var simpleDateFormat = SimpleDateFormat("MM-dd HH:mm")

        /**
         * 将时间2014-12-02 17:30:00 转化为 17:30
         *
         * @param t
         * @return
         */
        fun getFormatTime(t: String): String {
            if (TextUtils.isEmpty(t)) {
                return "--"
            }
            if (!t.contains(" ") || !t.contains(":")) {
                return t
            }
            val time: String
            val timeArray = t.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1].split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            time = timeArray[0] + ":" + timeArray[1]
            return time
        }

        /**
         * 将时间2014-12-02 17:30:00 转化为 今天 17:30
         *
         * @param t
         * @return
         */
        fun getFormatTimeforToday(t: String): String {
            if (TextUtils.isEmpty(t)) {
                return "--"
            }

            if (!t.contains(" ") || !t.contains(":")) {
                return t
            }

            val timeResult: String
            val timeDate = t.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (getTodayDate() == timeDate[0]) {
                timeResult = "今日"
            } else if (getNextDay(getTodayDate()) == timeDate[0]) {
                timeResult = "明日"
            } else {
                val a = timeDate[0].split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                //去掉日月前面的0
                val charsMonth = a[1].toCharArray()
                val charsDay = a[2].toCharArray()
                if (charsMonth[0] == '0') {
                    a[1] = charsMonth[1].toString()
                }
                if (charsDay[0] == '0') {
                    a[2] = charsDay[1].toString()
                }
                timeResult = a[1] + "月" + a[2] + "日"
            }

            //加时间 如：5：30
            //        String[] timeArray=timeDate[1].split(":");
            //        timeResult+=" "+timeArray[0]+":"+timeArray[1];
            return timeResult
        }

        /**
         * 将时间2014-12-02 17:30:00 转化为 2014-12-02 17:30
         *
         * @param t
         * @return
         */
        fun getFormatTime2(t: String): String {
            if (TextUtils.isEmpty(t)) {
                return "--"
            }

            if (!t.contains(":")) {
                return t
            }
            val time: String
            val timeArray = t.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            time = timeArray[0] + ":" + timeArray[1]
            return time
        }

        /**
         * 将时间2014-12-02 17:30:00 转化为 12-02 17:30
         *
         * @param t
         * @return
         */
        fun getFormatTime3(t: String): String {
            if (TextUtils.isEmpty(t)) {
                return "--"
            }

            if (!t.contains(":")) {
                return t
            }
            var time: String
            val timeArray = t.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val dayArray = timeArray[0].split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val hourArray = timeArray[1].split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            time = dayArray[1] + "-" + dayArray[2] + " "
            time += hourArray[0] + ":" + hourArray[1]
            return time
        }

        /**
         * 将时间2014-12-02 17:30:00 转化为 12月02日
         *
         * @param t
         * @return
         */
        @SuppressLint("SimpleDateFormat")
        fun getFormatTimeMd(t: String): String {
            try {
                val sdf = SimpleDateFormat("yyyy-MM-dd ")
                val date = sdf.parse(t)
                val sdf1 = SimpleDateFormat("MM月dd日")
                return sdf1.format(date)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return t
        }

        /**
         * 将时间2014-12-02 17:30:00 转化为 17:30
         *
         * @param t
         * @return
         */
        @SuppressLint("SimpleDateFormat")
        fun getFormatTimeHm(t: String): String {
            try {
                val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                val date = sdf.parse(t)
                val sdf1 = SimpleDateFormat("MM月dd日 HH:mm")
                return sdf1.format(date)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return t
        }


        /**
         * 将时间2014-12-02 17:30:00 转化为 12-02
         *
         * @param t
         * @return
         */
        fun getFormatTime4(t: String): String {
            if (TextUtils.isEmpty(t)) {
                return "--"
            }

            if (!t.contains("-")) {
                return t
            }
            val time: String
            val timeArray = t.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val dayArray = timeArray[0].split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            time = dayArray[1] + "-" + dayArray[2]
            return time
        }

        /**
         * 将时间2014-12-02 17:30:00 转化为 12月02日
         *
         * @param t
         * @return
         */
        fun getFormatTime5(t: String): String {
            if (TextUtils.isEmpty(t)) {
                return "--"
            }

            if (!t.contains("-")) {
                return t
            }
            val time: String
            val timeArray = t.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val dayArray = timeArray[0].split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            var dayCut0 = ""
            if (dayArray[1].substring(0, 1) == "0") {
                dayCut0 = dayArray[1].substring(1)
            } else {
                dayCut0 = dayArray[1]
            }
            var dayCut1 = ""
            if (dayArray[2].substring(0, 1) == "0") {
                dayCut1 = dayArray[2].substring(1)
            } else {
                dayCut1 = dayArray[2]
            }
            time = dayCut1 + "日" + dayCut0 + "月"
            return time
        }

        /**
         * 将时间转为代表"距现在多久之前"的字符串
         *
         * @param dateTime 格式为2014-12-02 17:30:00 +0800
         * @return
         */
        fun getStandardDate(dateTime: String): String {
            if (TextUtils.isEmpty(dateTime)) {
                return ""
            }
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
            var createTimeLong: Long = 0
            var currentTimeLong: Long = 0
            try {
                val dateCur = Date(System.currentTimeMillis())
                currentTimeLong = sdf.parse(sdf.format(dateCur)).time
                createTimeLong = sdf.parse(dateTime).time
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            //时间间隔
            val timeGaps = currentTimeLong - createTimeLong
            //创建的日期
            val dateCreate = Date(createTimeLong)
            val format = SimpleDateFormat("yyyy-MM-dd")
            val dateCreateDate = format.format(dateCreate)
            //当前日期
            val currentDay = getTodayDate()
            val day = timeGaps / 24 / 60 / 60 / 1000//天前

            if (day >= 2) {
                //x年x日
                val format1 = SimpleDateFormat("dd日MM月")
                return format1.format(dateCreate)
            } else {
                if (dateCreateDate == getLastDay(currentDay)) {
                    return "昨天"
                } else {
                    val hour = timeGaps / 60 / 60 / 1000 - day * 24// 小时
                    if (hour >= 1) {
                        return hour.toString() + "小时前"
                    } else {
                        val minute = timeGaps / 60 / 1000 - day * 24 * 60// 分钟前
                        return if (minute >= 1) {
                            minute.toString() + "分钟前"
                        } else {
                            "刚刚"
                        }
                    }
                }
            }
        }

        /**
         * 计算前一天的时间
         */
        fun getLastDay(dateStr: String): String {
            if (!TextUtils.isEmpty(dateStr)) {
                val df = SimpleDateFormat("yyyy-MM-dd")
                var resultDay = ""
                try {
                    val d = df.parse(dateStr)
                    resultDay = df.format(Date(d.time - 24 * 60 * 60 * 1000))
                } catch (e: ParseException) {
                    e.printStackTrace()
                } catch (e: NullPointerException) {
                    e.printStackTrace()
                }

                return resultDay

            } else {
                return ""
            }

        }

        /**
         * 汉字获得前一天
         */
        fun getLastHanziDay(dateStr: String): String {
            if (!TextUtils.isEmpty(dateStr)) {
                val df = SimpleDateFormat("yyyy年MM月dd日")
                var resultDay = ""
                try {
                    val d = df.parse(dateStr)
                    resultDay = df.format(Date(d.time - 24 * 60 * 60 * 1000))
                } catch (e: ParseException) {
                    e.printStackTrace()
                } catch (e: NullPointerException) {
                    e.printStackTrace()
                }

                return resultDay

            } else {
                return ""
            }

        }

        /**
         * 计算后一天的时间
         */
        fun getNextDay(dateStr: String): String {
            if (!TextUtils.isEmpty(dateStr)) {
                val df = SimpleDateFormat("yyyy-MM-dd")
                var resultDay = ""
                try {
                    val d = df.parse(dateStr)
                    resultDay = df.format(Date(d.time + 24 * 60 * 60 * 1000))
                } catch (e: ParseException) {
                    e.printStackTrace()
                }

                return resultDay
            } else {
                return ""
            }

        }

        /**
         * 当前时间戳
         */
        fun getSecondTime(): String {
            val re_time: String
            val currentTime = System.currentTimeMillis()
            val d = Date(currentTime)
            val l = d.time
            val str = l.toString()
            re_time = str.substring(0, 10)
            return re_time
        }

        /**
         * 获取当前时间
         *
         * @return
         */
        fun getCollectProgramTime(): String {
            val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val d = Date(System.currentTimeMillis())
            return df.format(d)
        }

        /**
         * 将时间转化需要的字符 小时前 分钟前 刚刚
         *
         * @param time
         * @return
         */
        fun getInformationTime(time: String): String {
            if (TextUtils.isEmpty(time)) {
                return ""
            }
            val currentTime = System.currentTimeMillis()
            val d = Date(currentTime)
            val now_time = d.time

            val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            try {
                val create_time = df.parse(time)
                val l = now_time - create_time.time
                val day = l / (24 * 60 * 60 * 1000)

                if (day >= 1) {
                    val df2 = SimpleDateFormat("MM-dd HH:mm")
                    try {
                        return df2.format(df.parse(time))
                    } catch (e: ParseException) {
                        e.printStackTrace()
                    }

                }

                val hour = l / (60 * 60 * 1000) - day * 24

                if (hour >= 1) {
                    return hour.toString() + "小时前"
                }

                val min = l / (60 * 1000) - day * 24 * 60 - hour * 60

                return if (min >= 1) {
                    min.toString() + "分钟前"
                } else "刚刚"

            } catch (e: ParseException) {
                e.printStackTrace()
                return ""
            }

        }

        /**
         * 得到当前时间 yyyy-mm-dd
         */
        fun getTodayDate(): String {
            val sDateFormat = SimpleDateFormat("yyyy-MM-dd")
            return sDateFormat.format(java.util.Date())
        }


        /**
         * 得到当前时间 yyyy年mm月dd
         */
        @SuppressLint("SimpleDateFormat")
        fun getTodayHanziDate(): String {
            val sDateFormat = SimpleDateFormat("yyyy年MM月dd日")
            return sDateFormat.format(java.util.Date())
        }

        /**
         * 将时间转化为星期
         *
         * @param pTime
         * @return
         */
        fun getWeekofday(pTime: String): String {
            if (TextUtils.isEmpty(pTime)) {
                return ""
            }
            try {
                var Week = ""

                val format = SimpleDateFormat("yyyy-MM-dd")
                val c = Calendar.getInstance()
                try {

                    c.time = format.parse(pTime)

                } catch (e: ParseException) {
                    e.printStackTrace()
                }

                if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    Week += "日"
                }
                if (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                    Week += "一"
                }
                if (c.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                    Week += "二"
                }
                if (c.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                    Week += "三"
                }
                if (c.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                    Week += "四"
                }
                if (c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                    Week += "五"
                }
                if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                    Week += "六"
                }

                return "周" + Week
            } catch (e: NullPointerException) {
                e.printStackTrace()
                return ""
            }


        }


        /**
         * 倒计时
         */
        fun ShowCountDownTime(millis: Long): String {
            val Second = millis / 1000
            val day = DueDate(Second)!!.Day
            val hour = DueDate(Second)!!.hour + day * 24
            val min = DueDate(Second)!!.min
            val s = DueDate(Second)!!.s
            var strHour = "" + hour
            var strMin = "" + min
            var strS = "" + s
            if (hour < 10) {
                strHour = "0" + hour
            }
            if (min < 10) {
                strMin = "0" + min
            }
            if (s < 10) {
                strS = "0" + s
            }
            return "$strHour:$strMin:$strS"
        }

        /**
         * 将时间转化为天小时分秒
         *
         * @param deadline
         * @return
         */
        fun DueDate(deadline: Long): MyData? {

            val l = deadline * 1000
            if (l < 0) {
                return null
            } else {
                val day = l / (24 * 60 * 60 * 1000)
                val hour = l / (60 * 60 * 1000) - day * 24
                val min = l / (60 * 1000) - day * 24 * 60 - hour * 60
                val s = l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60
                return MyData(day, hour, min, s, 0)
            }
        }

        /**
         * 将时间2014-12-02 17:30:00 +800 转化为 今天 n点n分
         *
         * @param t
         * @return
         */
        fun getProgramCloseTimeFormatTime(t: String): String {
            if (TextUtils.isEmpty(t)) {
                return "--"
            }

            if (!t.contains(" ") || !t.contains(":")) {
                return t
            }

            var timeResult: String
            val timeDate = t.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (getTodayDate() == timeDate[0]) {
                timeResult = "今日\n"
            } else if (getNextDay(getTodayDate()) == timeDate[0]) {
                timeResult = "明日\n"
            } else {
                val a = timeDate[0].split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                timeResult = a[1] + "月" + a[2] + "日\n"
            }

            val time = timeDate[1].split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

            timeResult += time[0] + "点" + time[1] + "分"

            return timeResult
        }

        /**
         * 圈子用 将时间2014-12-02 17:30:00 +800 转化为 今天 n点n分
         */
        fun getMessageFormatTime(t: String): String {

            if (TextUtils.isEmpty(t)) {
                return "--"
            }

            if (!t.contains(" ") || !t.contains(":")) {
                return t
            }

            var timeResult: String
            val timeDate = t.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val year_mouth_day = timeDate[0].split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

            if (getTodayDate() == timeDate[0]) {
                timeResult = "今天 "
            } else if (getLastDay(getTodayDate()) == timeDate[0]) {
                timeResult = "昨天 "
            } else {
                if (year_mouth_day[0] == getTodayDate().split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]) {
                    timeResult = year_mouth_day[1] + "月" + year_mouth_day[2] + "日 "
                } else {
                    timeResult = year_mouth_day[0] + "年" + year_mouth_day[1] + "月" + year_mouth_day[2] + "日 "
                }
            }

            val time = timeDate[1].split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

            timeResult += time[0] + "点" + time[1] + "分"

            return timeResult
        }

        /**
         * 账户余额用
         */
        fun geAccountFormatTime(t: String): String {
            //2014-12-02 17:30:00 +800 => 今天 昨天
            if (TextUtils.isEmpty(t)) {
                return "--"
            }

            val timeResult: String

            if (getTodayHanziDate() == t) {
                timeResult = "今天 "
            } else if (getLastHanziDay(getTodayHanziDate()) == t) {
                timeResult = "昨天 "
            } else {
                timeResult = t
            }

            return timeResult
        }

        /**
         * 将时间2014-12-02 17:30:00 +800 转化为 年月日
         *
         * @param t
         * @return
         */
        fun getFriendFormatTime(t: String): String {
            //2014-12-02 17:30:00 +800 => 年月日
            if (TextUtils.isEmpty(t)) {
                return "--"
            }

            if (!t.contains(" ") || !t.contains(":")) {
                return t
            }

            val timeResult: String
            val timeDate = t.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val year_mouth_day = timeDate[0].split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            timeResult = year_mouth_day[0] + "年" + year_mouth_day[1] + "月" + year_mouth_day[2] + "日 "
            return timeResult
        }


        /**
         * 当前时间戳  第几节/加时1 09:23.5
         */
        fun getBasketballLiveFormatTime(time: String): String {
            var formatTime = ""
            if (time.contains(" ")) {
                val t1 = time.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                formatTime += t1[0] //文字
                if (t1.size > 1) {
                    formatTime += " "
                    val t2 = t1[1].split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()  //09:23.5
                    var timeNum: String
                    if (t2[0] == "00") { //分钟
                        if (t2[1].contains(".")) {
                            timeNum = t2[1].replace(".", "\"")
                        } else {
                            timeNum = t2[1] + "\""
                        }

                    } else {
                        timeNum = t1[1].split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
                        timeNum = timeNum.replace(":", "\'")
                        timeNum += "\""
                    }

                    formatTime += timeNum
                }

            } else {
                formatTime = time

            }
            return formatTime.trim { it <= ' ' }
        }

        /**
         * 将时间转化为 17:30:00 +0800=> 17:30
         *
         * @param t
         * @return
         */
        fun getSocialBetFial(t: String): String {
            //2014-12-02 17:30:00 +0800=> 17:30
            if (TextUtils.isEmpty(t)) {
                return "--"
            }
            if (!t.contains(" ") || !t.contains(":")) {
                return t
            }
            val time: String
            val timeArray1 = t.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0].split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val timeArray2 = t.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1].split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val month = if (timeArray1[1].indexOf("0", 0) == 0) timeArray1[1].replace("0", "") else timeArray1[1]
            val day = if (timeArray1[2].indexOf("0", 0) == 0) timeArray1[2].replace("0", "") else timeArray1[2]
            time = month + "月" + day + "日 " +
                    timeArray2[0] + ":" + timeArray2[1].replace("分", "")
            LogUtils.d("timeeee", time)
            return time
        }


        /**
         * 得到两个时间差的毫秒数
         */
        fun getMilliSecondTime(t: String): Long {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

            try {
                val date = sdf.parse(t)
                val time = date.time

                return time - System.currentTimeMillis()
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return 0
        }


        /**
         * 得到当前时间的毫秒数
         */
        fun getCurrentMilliSecondTime(t: String): Long {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

            try {
                val date = sdf.parse(t)
                return date.time
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return 0
        }

        /**
         * 返回年份
         *
         * @param createTime
         * @return
         */
        fun getFormatYear(createTime: String): String {
            return if (TextUtils.isEmpty(createTime)) {
                "--"
            } else createTime.substring(0, 4) + "年"
        }

        /**
         * 获取系统当前时间的年份
         *
         * @return
         */
        fun getSystemTime(): String {
            val timeMillis = System.currentTimeMillis()
            val dateFormat = SimpleDateFormat("yyyy年MM月dd日")
            val format = dateFormat.format(timeMillis)
            return format.substring(0, 4)
        }
    }

}