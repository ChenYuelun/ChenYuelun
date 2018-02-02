package com.chen.chenyuelun.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import com.chen.chenyuelun.R
import com.chen.chenyuelun.data.model.HomeMenuItemBean
import com.chen.chenyuelun.data.model.HomeMenuResponse
import com.chen.chenyuelun.data.network.ParamsMapValue
import com.chen.chenyuelun.data.single.AppApplication
import com.chen.chenyuelun.data.single.AppInfo
import com.chen.libraryresouse.base.MainTag
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by chenyuelun on 2018/1/31.
 */
class NavigationBarUtils private constructor() {
    companion object {
        val instance = NavigationBarUtils()
    }


    fun getData(): List<HomeMenuItemBean> {
        val items = mutableListOf<HomeMenuItemBean>()
        val any = AppInfo.instance.getCacheManager().getAsAny(ParamsMapValue.CMD_HOME_DYNAMIC_MENU)
        if (any != null && any is HomeMenuResponse) {
            try {
                for (homeMenuText in any.resp[0].textList) {
                    val itemBean = HomeMenuItemBean(
                            homeMenuText.text,
                            getBitmap(homeMenuText.normalUrl)!!,
                            getBitmap(homeMenuText.pressUrl)!!,
                            Color.parseColor(any.resp[0].normalColor),
                            Color.parseColor(any.resp[0].pressColor),
                            homeMenuText.positionTag,
                            false
                    )
                    items.add(itemBean)
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                defaultData(items)
            }

        } else {
            defaultData(items)
        }
        return items
    }

    private fun defaultData(items: MutableList<HomeMenuItemBean>) {

        items.add(HomeMenuItemBean(
                "比赛预测",
                idToBitmap(R.drawable.analyse_normal),
                idToBitmap(R.drawable.analyse_press),
                R.color.navigationbar_normal_color,
                R.color.navigationbar_press_color,
                MainTag.FORECAST.tag,
                false
        ))
        items.add(HomeMenuItemBean(
                "方案",
                idToBitmap(R.drawable.plan_normal),
                idToBitmap(R.drawable.plan_press),
                R.color.navigationbar_normal_color,
                R.color.navigationbar_press_color,
                MainTag.FORECAST.tag,
                false
        ))
        items.add(HomeMenuItemBean(
                "圈子",
                idToBitmap(R.drawable.information_normal),
                idToBitmap(R.drawable.information_press),
                R.color.navigationbar_normal_color,
                R.color.navigationbar_press_color,
                MainTag.FORECAST.tag,
                false
        ))
        items.add(HomeMenuItemBean(
                "我",
                idToBitmap(R.drawable.me_normal),
                idToBitmap(R.drawable.me_press),
                R.color.navigationbar_normal_color,
                R.color.navigationbar_press_color,
                MainTag.FORECAST.tag,
                false
        ))


    }


    fun savaData(data: HomeMenuResponse) {
        AppInfo.instance.getCacheManager().put(ParamsMapValue.CMD_HOME_DYNAMIC_MENU, data)
        for (homeMenuText in data.resp[0].textList) {
            savaBitmap(homeMenuText.normalUrl)
            savaBitmap(homeMenuText.pressUrl)
        }
    }

    private fun getBitmap(imageUrl: String): Bitmap? {
        return AppInfo.instance.getCacheManager().getAsBitmap(imageUrl)
    }

    private fun savaBitmap(imageUrl: String) {
        if (getBitmap(imageUrl) == null) {
            Thread(Runnable {
                val image = getImageInputStream(imageUrl)
                if (image != null) {
                    AppInfo.instance.getCacheManager().put(imageUrl, image)
                }

            }).start()

        }
    }

    /**
     * 获取网络图片 转换为bitmap
     *
     * @param imageUrl 图片网络地址
     * @return Bitmap 返回位图
     */
    private fun getImageInputStream(imageUrl: String): Bitmap? {
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

    private fun idToBitmap(id: Int): Bitmap {
        return BitmapFactory.decodeResource(AppApplication.instance().resources, id)
    }
}