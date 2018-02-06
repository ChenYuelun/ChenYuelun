package com.chen.libraryresouse.utils

import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import java.util.*

/**
 * Created by chenyuelun on 2018/2/6.
 */
class AnimationUtils{

    companion object {
        /**
         * 获取透明度动画
         *
         * @param from
         * @param to
         * @param number
         * @return
         */
        fun getAlphaAnimationList(from: Int, to: Int, durationTime: Long, number: Int): List<AnimationSet> {
            val alphaAnimations = ArrayList<AnimationSet>()
            for (i in 0 until number) {
                val offset = getRandomNumber(5) * 100
                val set = AnimationSet(true)
                val alphaAnimation0 = AlphaAnimation(to.toFloat(), from.toFloat())
                alphaAnimation0.duration = durationTime / 2
                alphaAnimation0.startOffset = offset.toLong()
                set.addAnimation(alphaAnimation0)
                val alphaAnimation1 = AlphaAnimation(from.toFloat(), to.toFloat())
                alphaAnimation1.duration = durationTime / 2
                alphaAnimation1.startOffset = durationTime / 2 + offset
                set.addAnimation(alphaAnimation1)
                set.fillAfter = true
                alphaAnimations.add(set)
            }
            return alphaAnimations
        }


        /**
         * 获取4 - number+4之间的随机数
         *
         * @return
         */
        fun getRandomNumber(number: Int): Int {
            val random = Random()
            val nextInt = random.nextInt(number)
            return nextInt + 2
        }
    }
}