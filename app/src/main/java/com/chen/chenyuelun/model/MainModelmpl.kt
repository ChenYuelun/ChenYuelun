package com.chen.chenyuelun.model

import android.graphics.Bitmap
import android.graphics.Color
import com.chen.chenyuelun.R
import com.chen.chenyuelun.data.entity.HomeMenuItemBean
import com.chen.chenyuelun.data.entity.HomeMenuResponse
import com.chen.chenyuelun.data.single.AppApplication
import com.chen.chenyuelun.data.single.AppInfo
import com.chen.chenyuelun.network.ParamsMapValue
import com.chen.libraryresouse.base.EnumMainTag
import com.chen.libraryresouse.base.IModel
import com.chen.libraryresouse.utils.ImageLoader

/**
 * Created by chenyuelun on 2018/2/8.
 */
class MainModelmpl :IModel<Any>{
    override fun loadCache(listener: IModel.OnDataLoadListener<Any>) {

    }

    override fun loadNetwork(listener: IModel.OnDataLoadListener<Any>) {

    }

    fun readNavigtionData(listener: IModel.OnDataLoadListener<List<HomeMenuItemBean>>){
        val navigationData = getNavigationData()
        if (listener!= null){
            listener.onComplete(navigationData)
        }
    }

    fun getNavigationData(): List<HomeMenuItemBean> {
        val items = mutableListOf<HomeMenuItemBean>()
        val any = AppInfo.instance.getCacheManager().getAsAny(ParamsMapValue.CMD_HOME_DYNAMIC_MENU)
        if (any != null && any is HomeMenuResponse) {
            try {
                for (homeMenuText in any.resp[0].textList) {
                    val itemBean = HomeMenuItemBean(
                            homeMenuText.text,
                            ImageLoader.getBitmap(AppApplication.instance(),homeMenuText.normalUrl)!!,
                            ImageLoader.getBitmap(AppApplication.instance(),homeMenuText.pressUrl)!!,
                            Color.parseColor(any.resp[0].normalColor),
                            Color.parseColor(any.resp[0].pressColor),
                            homeMenuText.positionTag,
                            false
                    )
                    items.add(itemBean)
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                defaultNavigationData(items)
            }

        } else {
            defaultNavigationData(items)
        }
        return items
    }

    private fun defaultNavigationData(items: MutableList<HomeMenuItemBean>) {
        val defaultNormalColor = "#BCBBBB"
        val defaultPressColor = "#5CC3FF"
        items.add(HomeMenuItemBean(
                "比赛预测",
                ImageLoader.idToBitmap(R.drawable.analyse_normal),
                ImageLoader.idToBitmap(R.drawable.analyse_press),
                Color.parseColor(defaultNormalColor),
                Color.parseColor(defaultPressColor),
                EnumMainTag.FORECAST.tag,
                false
        ))
        items.add(HomeMenuItemBean(
                "方案",
                ImageLoader.idToBitmap(R.drawable.plan_normal),
                ImageLoader.idToBitmap(R.drawable.plan_press),
                Color.parseColor(defaultNormalColor),
                Color.parseColor(defaultPressColor),
                EnumMainTag.PLAN.tag,
                false
        ))
        items.add(HomeMenuItemBean(
                "圈子",
                ImageLoader.idToBitmap(R.drawable.information_normal),
                ImageLoader.idToBitmap(R.drawable.information_press),
                Color.parseColor(defaultNormalColor),
                Color.parseColor(defaultPressColor),
                EnumMainTag.SOCIAL.tag,
                false
        ))
        items.add(HomeMenuItemBean(
                "我",
                ImageLoader.idToBitmap(R.drawable.me_normal),
                ImageLoader.idToBitmap(R.drawable.me_press),
                Color.parseColor(defaultNormalColor),
                Color.parseColor(defaultPressColor),
                EnumMainTag.ME.tag,
                false
        ))

    }
}