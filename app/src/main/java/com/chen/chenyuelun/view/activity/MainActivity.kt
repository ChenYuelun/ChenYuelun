package com.chen.chenyuelun.view.activity

import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.KeyEvent
import com.chen.chenyuelun.R
import com.chen.chenyuelun.adapter.HomeMenuRvAdapter
import com.chen.chenyuelun.data.entity.HomeMenuItemBean
import com.chen.chenyuelun.presenter.MainPresenter
import com.chen.chenyuelun.utils.IntentParams
import com.chen.chenyuelun.view.fragment.*
import com.chen.libraryresouse.base.*
import com.chen.libraryresouse.base.mvp.BaseActiviy
import com.chen.libraryresouse.base.mvp.IView
import com.chen.libraryresouse.utils.LogUtils
import com.chen.libraryresouse.utils.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActiviy<MainActivity, MainPresenter<MainActivity>>(), IView {


    private lateinit var navigationData :List<HomeMenuItemBean>

    private val fragmentMap = mutableMapOf<String, Fragment>()

    //正在前台的fragment
    private var showedFragment: Fragment? = null

    override fun getLayoutId() = R.layout.activity_main

    override fun getTitleLayout() = null

    override fun createPresenter(): MainPresenter<MainActivity> {
        return MainPresenter()
    }


    override fun showLoading() {

    }



    override fun setUp() {
        mPresenter.fetch()
    }
    override fun  showData(data: Any) {

    }

    fun initNavigationAndFragments(navigationData :List<HomeMenuItemBean>){
        this.navigationData = navigationData
        rv_main_navigation.layoutManager = GridLayoutManager(this, navigationData.size)
        val adapter = HomeMenuRvAdapter(this, navigationData)
        LogUtils.d("dddddddddddd",navigationData.toString())
        rv_main_navigation.adapter = adapter
        navigationData.forEach {
            when (it.postionTag) {
                EnumMainTag.FORECAST.tag -> this.fragmentMap[it.postionTag] = MainForacastFragment()
                EnumMainTag.PLAN.tag -> this.fragmentMap[it.postionTag] = MainPlanFragment()
                EnumMainTag.SOCIAL.tag -> this.fragmentMap[it.postionTag] = MainSocialFragment()
                EnumMainTag.ME.tag -> this.fragmentMap[it.postionTag] = MainMeFragment()
                EnumMainTag.WEB.tag -> this.fragmentMap[it.postionTag] = MainWebFragment()
            }
        }

        adapter.changSlectItem(EnumMainTag.FORECAST.tag)
    }



    //根据tag显示要展示的界面
    fun changeFragmentShow(tag: String = EnumMainTag.FORECAST.tag) {
        //根据tag获得将要显示的fragment
        val fragment = fragmentMap[tag]
        //如果为空  直接返回
        if (fragment == showedFragment) return

        if (fragment != null) {
            //开启事务
            val transaction = supportFragmentManager.beginTransaction()
            if (!fragment.isAdded) {
                //将要添加的fragment未添加到布局
                transaction.add(R.id.fl_main, fragment, tag)
                if (showedFragment != null)
                //隐藏上次显示的
                    transaction.hide(showedFragment)
            } else {
                //隐藏上次显示的
                transaction.hide(showedFragment)
                //显示
                transaction.show(fragment)
            }
            showedFragment = fragment
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
        fun start(context: Context, tag: String = EnumMainTag.FORECAST.tag) {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(IntentParams.MAIN_TAG, tag)
            context.startActivity(intent)
        }
    }

}
