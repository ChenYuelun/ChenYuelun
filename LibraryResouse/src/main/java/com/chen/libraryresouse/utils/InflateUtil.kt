package com.chen.libraryresouse.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by chenyuelun on 2018/1/22.
 * 扩展 inflate方法
 */
fun ViewGroup.inflate(layoutId: Int): View =
        LayoutInflater.from(context).inflate(layoutId, this, false)