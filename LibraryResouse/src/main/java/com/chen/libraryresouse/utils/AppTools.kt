package com.chen.libraryresouse.utils

/**
 * Created by chenyuelun on 2018/1/20.
 */
class AppTools private constructor(){
    companion object {
        /**
         * 获取当前时间戳字符串
         */
        fun getCurrentTimeMillis(): String {
            val currentTime = System.currentTimeMillis()
            val str = currentTime.toString()
            return str.substring(0, 10)
        }

        //每次发送接口都有 六个随机数 区分大小写的26个英文字母+数字
        fun getTraceId(): String {
            var trace_id = ""
            val all = arrayOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0")
            var a = 0
            val size = 6
            while (a < size) {
                val s = (Math.random() * all.size).toInt()
                trace_id += all[s]
                a++
            }
            return trace_id
        }
    }
}