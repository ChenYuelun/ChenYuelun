package com.chen.chenyuelun.view.fragment

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.chen.chenyuelun.R
import com.chen.chenyuelun.adapter.MainForecastRvAdapter
import com.chen.chenyuelun.data.entity.*
import com.chen.chenyuelun.presenter.ForecastFragmentPresenter
import com.chen.chenyuelun.view.activity.WebH5Activity
import com.chen.libraryresouse.base.mvp.BaseFragment
import com.chen.libraryresouse.base.mvp.IView
import kotlinx.android.synthetic.main.layout_fragment_main_foracast.*
import kotlinx.android.synthetic.main.refresh_layout.*

/**
 * Created by chenyuelun on 2018/2/2.
 */
class MainForacastFragment : BaseFragment<MainForacastFragment, ForecastFragmentPresenter<MainForacastFragment>>(), IView {


    val data = HomeForecastData()

    override fun getLayoutId() = R.layout.layout_fragment_main_foracast

    override fun getTitleLyout() = titleLayout

    override fun createPresenter(): ForecastFragmentPresenter<MainForacastFragment> {
        return ForecastFragmentPresenter()
    }

    override fun setUp() {
        super.setUp()
        val adapter = MainForecastRvAdapter(context!!, data)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
        iv_serach_forecast.setOnClickListener {
            startActivity(Intent(context, WebH5Activity::class.java))

        }
        mPresenter.fetch()
    }

    override fun showLoading() {
        recyclerView.adapter.notifyDataSetChanged()
    }

    var isRefrsh = false
    var isFoot = false
    override fun showData(data: Any) {
        when (data) {

            is CaiqiuFocusList -> this.data.caiqiuFocusList = data

            is TopNavigationList -> this.data.topNavigationList = data

            is MarqueeList -> this.data.marqueeList = data

            is HotMatchs -> this.data.hotMatchs = data

            is RecordBean -> this.data.recordList = data

            is AdvertisBean -> this.data.advertisBean = data

            is SeasonList -> this.data.guessYouLikeList = data

            is BasketballLiveUrl -> this.data.basketballLiveUrl.url = data.url

            is FootballLiveUrl -> this.data.footballLiveUrl.url = data.url
            is FootballMatchs -> {
                if (isRefrsh){
                    this.data.footballMatchs.matchs.clear()
                }
                this.data.footballMatchs.matchs.addAll(data.matchs)
            }
            is BasketballMatchs ->{
                if (isRefrsh){
                    this.data.basketballMatchs.matchs.clear()
                }
                this.data.basketballMatchs.matchs.addAll(data.matchs)
            }

        }
        recyclerView.adapter.notifyDataSetChanged()
    }

}