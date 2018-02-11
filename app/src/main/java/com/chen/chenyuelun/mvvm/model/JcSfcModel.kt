package com.chen.chenyuelun.mvvm.model

import com.chen.chenyuelun.data.entity.FootballListSportteryResponse
import com.chen.chenyuelun.network.request.FootballDateSportteryRequest
import com.chen.chenyuelun.network.request.FootballListSportteryRequest
import com.chen.librarynetwork.transformer.MyDefaultTransformer
import com.chen.libraryresouse.base.mvp.IModel
import com.chen.libraryresouse.utils.LogUtils
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by chenyuelun on 2018/2/9.
 */
class JcSfcModel : IModel<FootballListSportteryResponse> {

    override fun loadCache(listener: IModel.OnDataLoadListener<FootballListSportteryResponse>) {
    }

    override fun loadNetwork(listener: IModel.OnDataLoadListener<FootballListSportteryResponse>) {
        sendApiJCDate(listener)
    }

    private fun sendApiJCDate(listener: IModel.OnDataLoadListener<FootballListSportteryResponse>) {
        val request = FootballDateSportteryRequest()
        ServiceFactory.createRxRetrofitService()
                .getFootballDateSporttery(request.getSign(), request.getRequestMap())
                .compose(MyDefaultTransformer())
                .subscribeBy(
                        onNext = {
                            LogUtils.d("竞彩日历数据请求成功" + it.code)
                            sendApiJcList(listener,it.resp[1].date)
                        }
                        ,
                        onError = {
                            it.printStackTrace()
                            LogUtils.d("竞彩日历数据请求失败" +it.message)
                        }
                )
    }


    private fun sendApiJcList(listener: IModel.OnDataLoadListener<FootballListSportteryResponse>, date :String){
        val request = FootballListSportteryRequest(date)
        ServiceFactory.createRxRetrofitService()
                .getFootballListSporttery(request.getSign(),request.getRequestMap())
                .compose(MyDefaultTransformer())
                .subscribeBy(
                        onNext = {
                            LogUtils.d("竞彩比赛数据成功" + it.code)
                            listener.onComplete(it)
                        }
                        ,
                        onError = {
                            it.printStackTrace()
                            LogUtils.d("竞彩比赛数据失败" +it.message)
                        }
                )
    }

}