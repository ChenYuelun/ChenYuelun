package com.chen.chenyuelun.data.entity

import android.graphics.Bitmap
import java.io.Serializable

/**
 * Created by chenyuelun on 2018/1/31.
 */
data class HomeMenuItemBean(val text:String,
                            val normalBitmap: Bitmap,
                            val pressBitmap:Bitmap,
                            val normalColor: Int,
                            val pressColor: Int,
                            val postionTag:String,
                            var isSelected: Boolean = false

) : Serializable