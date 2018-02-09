package com.chen.chenyuelun.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.chen.chenyuelun.R
import com.chen.libraryresouse.base.BaseActiviy2
import kotlinx.android.synthetic.main.activity_jc_and_sfc.*

class JcAndSfcActivity : BaseActiviy2() {

    override fun getTitleLayout() = this.titleLayout

    override fun setUp() {

    }

    override fun getLayoutId() = R.layout.activity_jc_and_sfc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jc_and_sfc)
    }
}
