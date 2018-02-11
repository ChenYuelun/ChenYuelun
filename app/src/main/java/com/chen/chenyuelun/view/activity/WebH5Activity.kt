package com.chen.chenyuelun.view.activity

import android.graphics.Bitmap
import android.os.Handler
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.widget.FrameLayout
import com.chen.chenyuelun.R
import com.chen.chenyuelun.presenter.WebPresenter
import com.chen.libraryresouse.base.mvp.BaseActiviy
import com.chen.libraryresouse.base.mvp.IView
import com.tencent.smtt.export.external.interfaces.SslError
import com.tencent.smtt.export.external.interfaces.SslErrorHandler
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.android.synthetic.main.activity_web_h5.*

class WebH5Activity : BaseActiviy<WebH5Activity, WebPresenter>(), IView {


    override fun createPresenter(): WebPresenter {
        return WebPresenter()
    }

    var loadUrl = ""
    val UPDATA_PROGRESS = 0
    val UPDATA_TITLE = 1

    override fun getLayoutId() = R.layout.activity_web_h5
    override fun getTitleLayout() = titleLayout
    var mWebView :WebView? = null
    override fun setUp() {
        initWebView()
        mWebView!!.loadUrl("https://m.baidu.com/")
    }

    val handler = Handler() {
        when (it.what) {
            UPDATA_PROGRESS -> {
                val progress = it.obj as Int
                if (progress >= 0) {
                    webProgress.visibility = View.VISIBLE
                    webProgress.progress = progress
                    if (progress >=99){
                        webProgress.visibility = View.GONE
                    }
                } else {
                    webProgress.visibility = View.GONE
                }
            }
            UPDATA_TITLE -> {
                val title = it.obj as String
                tv_web_title.text = title
            }


        }

        return@Handler true
    }

    private fun initWebView() {
        mWebView = WebView(this)
        fl_content.addView(mWebView,0,FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT))
        val webSetting = mWebView!!.settings
        webSetting.allowFileAccess = true
        webSetting.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
        webSetting.setSupportZoom(true)
        webSetting.builtInZoomControls = true
        webSetting.useWideViewPort = true
        webSetting.setSupportMultipleWindows(false)
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true)
        // webSetting.setDatabaseEnabled(true);
        webSetting.domStorageEnabled = true
        webSetting.javaScriptEnabled = true
        webSetting.setGeolocationEnabled(true)
        webSetting.setAppCacheMaxSize(java.lang.Long.MAX_VALUE)
        webSetting.setAppCachePath(this.getDir("appcache", 0).path)
        webSetting.databasePath = this.getDir("databases", 0).path
        webSetting.setGeolocationDatabasePath(this.getDir("geolocation", 0)
                .path)
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.pluginState = WebSettings.PluginState.ON_DEMAND
//        mWebView.setWebChromeClient(MyWebChromeClient())
//        mWebView.webViewClient = MyWebViewClient()
        mWebView!!.webViewClient = (object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                handler.sendMessage(handler.obtainMessage(UPDATA_PROGRESS,0))
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                handler.sendMessage(handler.obtainMessage(UPDATA_PROGRESS,-1))
                super.onPageFinished(view, url)
            }

            override fun onReceivedSslError(p0: WebView?, p1: SslErrorHandler?, p2: SslError?) {
                p1!!.proceed()
            }
        })
        mWebView!!.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                handler.sendMessage(handler.obtainMessage(UPDATA_PROGRESS,newProgress))
                super.onProgressChanged(view, newProgress)
            }

            override fun onReceivedTitle(p0: WebView?, p1: String?) {
                val message = handler.obtainMessage()
                message.what =UPDATA_TITLE
                if (!TextUtils.isEmpty(p1) &&!p1!!.toUpperCase().contains("ERRO")&&!p1.contains("无法打开")){
                    message.obj = p1
                }else{
                    message.obj = ""
                }
                handler.sendMessage(message)
                super.onReceivedTitle(p0, p1)
            }

        }

    }



    fun goBack() : Boolean{
        if (mWebView != null && mWebView!!.canGoBack()){
            mWebView!!.goBack()
            return true
        }else{
            return false
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (goBack()){
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    class MyWebChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
        }
    }

    class MyWebViewClient : WebViewClient() {

    }

    override fun showLoading() {
    }

    override fun showData(data: Any) {
    }

}
