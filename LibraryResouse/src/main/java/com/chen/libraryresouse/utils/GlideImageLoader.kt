package com.chen.libraryresouse.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.youth.banner.loader.ImageLoader

/**
 * Created by chenyuelun on 2018/2/5.
 */
class GlideImageLoader : ImageLoader(){
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {

        Glide.with(context!!).load(path).into(imageView!!);
    }

}