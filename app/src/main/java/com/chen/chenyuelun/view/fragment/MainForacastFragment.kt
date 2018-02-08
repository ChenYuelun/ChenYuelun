package com.chen.chenyuelun.view.fragment

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.chen.chenyuelun.R
import com.chen.chenyuelun.adapter.MainForecastRvAdapter
import com.chen.chenyuelun.data.entity.HomeForecastData
import com.chen.chenyuelun.presenter.MainForecastPresenter
import com.chen.chenyuelun.view.BaseView
import com.chen.chenyuelun.view.activity.WebH5Activity
import com.chen.libraryresouse.base.BaseFragment
import kotlinx.android.synthetic.main.layout_fragment_main_foracast.*
import kotlinx.android.synthetic.main.refresh_layout.*

/**
 * Created by chenyuelun on 2018/2/2.
 */
class MainForacastFragment : BaseFragment(), BaseView {

    val data = HomeForecastData()
    private val presenter = MainForecastPresenter(this, data)

    override fun getLayoutId() = R.layout.layout_fragment_main_foracast

    override fun getTitleLyout() = titleLayout

    override fun setUp() {
        super.setUp()
        presenter.readDataFromCache()
        val adapter = MainForecastRvAdapter(context!!, data)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
        iv_serach_forecast.setOnClickListener {
            startActivity(Intent(context,WebH5Activity::class.java))

        }
    }

    override fun requestApi() {
        presenter.requestDataFromApi()
    }


    override fun onError(hasData: Boolean) {

    }

    override fun onSeccuess() {
        recyclerView.adapter.notifyDataSetChanged()
    }

}