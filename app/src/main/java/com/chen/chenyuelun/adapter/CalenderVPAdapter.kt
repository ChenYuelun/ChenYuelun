package com.chen.chenyuelun.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup

/**
 * Created by chenyuelun on 2018/2/9.
 */
class CalenderVPAdapter(val data: List<Fragment>, val fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int) = data[position]


    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getCount() = data.size


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return super.instantiateItem(container, position)
    }

}