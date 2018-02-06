package com.chen.libraryresouse.utils

import android.content.Context

/**
 * Created by ${ChenYuelun} on 2017/12/10.
 *
 *----------Dragon be here!----------/
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑
 * 　　　　┃　　　┃代码无BUG！
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━━━━━━
 *
 *说明：
 */

class DensityUtils{

    companion object {
        /**
         * dip转为 px
         */
        fun dip2px(context: Context,dipValue: Float): Float {
            val scale = context.resources.displayMetrics.density
            return (dipValue * scale + 0.5f)
        }


        /**
         * 将px值转换为sp值，保证文字大小不变
         */
        fun px2sp(context: Context,pxValue: Float): Int {
            val fontScale =  context.resources.displayMetrics.scaledDensity
            return (pxValue / fontScale + 0.5f).toInt()
        }

        /**
         * 将sp值转换为px值，保证文字大小不变
         */
        fun sp2px(context: Context,spValue: Float): Float {
            val fontScale =  context.resources.displayMetrics.scaledDensity
            return (spValue * fontScale + 0.5f)
        }
    }
}
