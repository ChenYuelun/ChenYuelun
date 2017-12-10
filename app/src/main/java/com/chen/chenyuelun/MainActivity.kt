package com.chen.chenyuelun

import android.os.Bundle
import com.chen.libraryresouse.base.BaseActiviy

class MainActivity : BaseActiviy() {

    override fun onCreate(savedInstanceState: Bundle?) {
        isNetNecessary = false
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
