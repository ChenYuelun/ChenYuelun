package com.chen.libraryresouse.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.net.HttpURLConnection
import java.net.URL
import android.databinding.BindingAdapter



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

        @BindingAdapter("image")
        fun imageLoader(imageView: ImageView, url: String) {
           loadImage(url, imageView)
        }


        /**
         * 获取网络图片 转换为bitmap
         *
         * @param imageUrl 图片网络地址
         * @return Bitmap 返回位图
         */
        fun getImageInputStream(imageUrl: String): Bitmap? {
            val url: URL
            val connection: HttpURLConnection
            var bitmap: Bitmap? = null
            try {
                url = URL(imageUrl)
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 6000 //超时设置
                connection.doInput = true
                connection.useCaches = false //设置不使用缓存
                val inputStream = connection.inputStream
                bitmap = BitmapFactory.decodeStream(inputStream)
                inputStream.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return bitmap
        }

        fun savaBitmap(context: Context,imageUrl: String) {
            if (getBitmap(context,imageUrl) == null) {
                Thread(Runnable {
                    val image = ImageLoader.getImageInputStream(imageUrl)
                    if (image != null) {
                       CacheManager[context].put(imageUrl, image)
                    }

                }).start()

            }
        }
         fun getBitmap(context: Context,imageUrl: String): Bitmap? {
            return  CacheManager[context].getAsBitmap(imageUrl)
        }

        fun idToBitmap(id: Int): Bitmap {
            return BitmapFactory.decodeResource(ActivityStack.getTopActivity().resources, id)
        }
    }
}