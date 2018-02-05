package com.chen.libraryresouse.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by chenyuelun on 2018/2/5.
 */
class ImageLoader private constructor(){

    companion object {
        fun loadImage(imageUrl:String,imageView :ImageView,errorImage :Int=0,placeHolder : Int=0){
            Glide.with(imageView.context)
                    .load(imageUrl)
                    .into(imageView)
        }
    }
}