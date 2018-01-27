package com.chen.libraryresouse.utils

import android.content.Context

/**
 * Created by chenyuelun on 2018/1/22.
 */
class SpUtils private constructor() {
    companion object {
        //存储的sharedPreferences文件名
        private val FILE_NAME = "appSaveFile"

        /**
         * 保存数据到文件
         *
         * @param key
         * @param data
         */
        fun saveData(context: Context, key: String, data: Any) {
            val sp = context
                    .getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
            val editor = sp.edit()

            when (data) {
                is Boolean -> editor.putBoolean(key, data)
                is String -> editor.putString(key, data)
                is Int -> editor.putInt(key, data)
                is Float -> editor.putFloat(key, data)
                is Long -> editor.putLong(key, data)
                else -> throw RuntimeException("只支持存取Boolean、String、Int、Float、Long类型的数据！")
            }
            editor.apply()
        }

        /**
         * 从文件中读取数据
         *
         * @param key
         * @param defValue
         * @return
         */
        fun getData(context: Context, key: String, defValue: Any): Any  {
            val sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
            //defValue为为默认值，如果当前获取不到数据就返回它
            return when (defValue) {
                is Boolean -> sp.getBoolean(key, defValue)
                is Long -> sp.getLong(key, defValue)
                is String -> sp.getString(key, defValue)
                is Float -> sp.getFloat(key, defValue)
                is Int -> sp.getInt(key, defValue)
                else -> throw RuntimeException("只支持存取Boolean、String、Int、Float、Long类型的数据！")
            }
        }

        /**
         * 删除指定的key的值
         */
        fun delete(context: Context, key: String) {
            val sharedPreferences = context
                    .getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.remove(key).apply()
        }
    }
}

