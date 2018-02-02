package com.chen.chenyuelun.view.activity

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.CountDownTimer
import android.support.v7.widget.GridLayoutManager
import android.view.KeyEvent
import android.view.View
import android.widget.ImageView
import com.chen.chenyuelun.R
import com.chen.chenyuelun.adapter.HomeMenuRvAdapter
import com.chen.chenyuelun.data.model.HomeMenuResponse
import com.chen.chenyuelun.data.single.AppApplication
import com.chen.chenyuelun.data.single.AppInfo
import com.chen.chenyuelun.utils.IntentParams
import com.chen.chenyuelun.utils.NavigationBarUtils
import com.chen.chenyuelun.view.fragment.*
import com.chen.libraryresouse.base.BaseActiviy
import com.chen.libraryresouse.base.BaseFragment
import com.chen.libraryresouse.base.MainTag
import com.chen.libraryresouse.utils.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActiviy() {


    private val navigationData = NavigationBarUtils.instance.getData()

    private val fragmentMap = mutableMapOf<String, BaseFragment>()

    override fun getLayoutId() = R.layout.activity_main

    override fun getTitleLayout() = null

    override fun setUp() {
        rv_main_navigation.layoutManager = GridLayoutManager(this, navigationData.size)!!
        val adapter = HomeMenuRvAdapter(this, navigationData)
        rv_main_navigation.adapter = adapter
        navigationData.forEach {
            when (it.postionTag) {
                MainTag.FORECAST.tag -> fragmentMap[it.postionTag] = MainForacastFragment()
                MainTag.PLAN.tag -> fragmentMap[it.postionTag] = MainPlanFragment()
                MainTag.SOCIAL.tag -> fragmentMap[it.postionTag] = MainSocialFragment()
                MainTag.ME.tag -> fragmentMap[it.postionTag] = MainMeFragment()
                MainTag.WEB.tag -> fragmentMap[it.postionTag] = MainWebFragment()
            }
        }

        adapter.changSlectItem(MainTag.FORECAST.tag)
    }

    override fun requestApi() {

    }

    //正在前台的fragment
    private var showedFragment: BaseFragment? = null

    //根据tag显示要展示的界面
    fun changeFragmentShow(tag: String = MainTag.FORECAST.tag) {
        //根据tag获得将要显示的fragment
        val baseFragment = fragmentMap[tag]
        //如果为空  直接返回
        if (baseFragment == showedFragment) return

        if (baseFragment != null) {
            //开启事务
            val transaction = supportFragmentManager.beginTransaction()
            if (!baseFragment.isAdded) {
                //将要添加的fragment未添加到布局
                transaction.add(R.id.fl_main, baseFragment, tag)
                if (showedFragment != null)
                //隐藏上次显示的
                    transaction.hide(showedFragment)
            } else {
                //隐藏上次显示的
                transaction.hide(showedFragment)
                //显示
                transaction.show(baseFragment)
            }
            showedFragment = baseFragment
            //提交事务
            transaction.commit()
        }


    }

    //双击退出
    var isExit = false

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                toast("再按一次退出软件")
                isExit = true
                object : CountDownTimer(2000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {}

                    override fun onFinish() {
                        isExit = false
                    }
                }.start()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    //伴生对象 里面类似于java的静态属性/方法
    companion object {
        //启动本fragment的方法
        fun start(context: Context, tag: String = MainTag.FORECAST.tag) {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(IntentParams.MAIN_TAG, tag)
            context.startActivity(intent)
        }
    }

}
