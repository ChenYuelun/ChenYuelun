package com.chen.chenyuelun.view.activity

import android.databinding.DataBindingUtil
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.View
import com.chen.chenyuelun.R
import com.chen.chenyuelun.adapter.CalenderVPAdapter
import com.chen.chenyuelun.databinding.ActivityJcAndSfcBinding
import com.chen.chenyuelun.view.fragment.CalenderJCMatchFragment
import com.chen.libraryresouse.base.mvvm.BaseActiviy2
import com.chen.libraryresouse.utils.toast
import kotlinx.android.synthetic.main.activity_jc_and_sfc.*

class JcAndSfcActivity : BaseActiviy2() {

    lateinit var binding: ActivityJcAndSfcBinding
    var currentIndex = 0
    val fragments = mutableListOf<Fragment>()

    override fun getBinding() {
        binding = DataBindingUtil.setContentView<ActivityJcAndSfcBinding>(this, R.layout.activity_jc_and_sfc)
    }
//    override fun getLayoutId() = R.layout.activity_jc_and_sfc

    override fun getTitleLayout() = this.titleLayout

    override fun setUp() {
        fragments.add(0, CalenderJCMatchFragment())
//        fragments.add(1, CalenderSFCMatchFragment())
        viewPager_JC_SFC.adapter = CalenderVPAdapter(fragments, supportFragmentManager)
        viewPager_JC_SFC.currentItem = 0
        viewPager_JC_SFC.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                currentIndex = position
                if (position == 0) {
                    tv_JC.setBackgroundResource(R.drawable.data_letf_btn_press)
                    tv_JC.setTextColor(resources.getColor(R.color.top_btn_press_color))
                    tv_SFC.setBackgroundResource(R.drawable.data_right_btn_normal)
                    tv_SFC.setTextColor(resources.getColor(R.color.top_btn_normal_color))

                } else {
                    tv_SFC.setBackgroundResource(R.drawable.data_right_btn_press)
                    tv_SFC.setTextColor(resources.getColor(R.color.top_btn_press_color))
                    tv_JC.setBackgroundResource(R.drawable.data_letf_btn_normal)
                    tv_JC.setTextColor(resources.getColor(R.color.top_btn_normal_color))
                }
            }
        })
        binding.llBack.setOnClickListener( {
            finish()
        })
        binding.llSearch.setOnClickListener {
            toast("搜索")
        }
    }

    fun onSelected(v: View) {
        if (v == tv_JC && currentIndex != 0) {
            viewPager_JC_SFC.currentItem = 0
        } else if (v == tv_SFC && currentIndex != 1) {
            viewPager_JC_SFC.currentItem = 1
        }
    }
}
