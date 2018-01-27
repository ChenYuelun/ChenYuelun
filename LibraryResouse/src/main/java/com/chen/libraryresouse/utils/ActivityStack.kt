package com.chen.libraryresouse.utils

import android.app.Activity
import android.app.Application
import java.util.*

/**
 * Created by chenyuelun on 2018/1/22.
 */
class ActivityStack {

    companion object {
        var needRestarWelcome = true
        private var application: Application? = null

        /**
         * 栈集合
         */
        private val activityStack = Stack<Activity>()

        fun initApplication(application: Application) {
            this.application = application
        }

        fun GlobalContext(): Application? {
            return application
        }



        /**
         * 添加activity到栈集合中
         *
         * @param activity 被添加的集合
         */
        fun addActivity(activity: Activity) {
            activityStack.add(activity)
        }

        /**
         * 移除指定的activity
         *
         * @param activity 被移除的activity
         */
        fun removeActivity(activity: Activity) {
            activity.finish()
            activityStack.remove(activity)
        }

        /**
         * 销毁所有的activity
         */
        fun finishAllActivity() {
            for (activity in activityStack) {
                if (!activity.isFinishing) {
                    activity.finish()
                }
            }
            activityStack.clear()
        }

        /**
         * 根据类名销毁activity
         *
         * @param cls 被销毁activity
         */
        fun finishActivityByClass(cls: Class<*>) {

            for (activity in activityStack) {
                if (activity.javaClass == cls) {
                    activity.finish()
                }
            }
        }

        /**
         * 根据类名销毁activity
         *
         * @param cls 被销毁activity
         */
        fun finishActivityByClassInt(cls: Class<*>) {
            var n = 0
            for (i in activityStack.indices) {
                if (activityStack[i].javaClass == cls) {
                    if (n != 0) {
                        activityStack[i].finish()
                    }
                    n++
                }
            }
        }


        /**
         * 获取顶部的activity
         *
         * @return 返回顶部的activity
         */
        fun getTopActivity(): Activity {
            return activityStack.lastElement()
        }

        /**
         * 获取顶部的activity
         *
         * @return 返回顶部的activity
         */
        fun getTop2Activity(): Activity {
            return activityStack[activityStack.size - 2]
        }
    }
}