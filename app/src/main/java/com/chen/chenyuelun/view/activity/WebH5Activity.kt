package com.chen.chenyuelun.view.activity

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Handler
import android.text.TextUtils
import android.view.View
import com.chen.chenyuelun.R
import com.chen.libraryresouse.base.BaseActiviy
import com.tencent.smtt.export.external.interfaces.SslError
import com.tencent.smtt.export.external.interfaces.SslErrorHandler
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.android.synthetic.main.activity_web_h5.*

class WebH5Activity : BaseActiviy() {
    var loadUrl = ""
    val UPDATA_PROGRESS = 0
    val UPDATA_TITLE = 1
    override fun getTitleLayout() = titleLayout

    override fun initIntentData() {
        loadUrl = "https://www.baidu.com/"
    }

    override fun setUp() {
        initWebView()
        mWebView.loadUrl("https://m.baidu.com/")
    }

    val handler = Handler() {
        when (it.what) {
            UPDATA_PROGRESS -> {
                val progress = it.obj as Int
                if (progress >= 0) {
                    webProgress.visibility = View.VISIBLE
                    webProgress.progress = progress
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

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {

        val webSetting = mWebView.settings
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
        mWebView.webViewClient = (object : WebViewClient() {
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
        mWebView.webChromeClient = object : WebChromeClient() {
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

    override fun getLayoutId() = R.layout.activity_web_h5

    override fun requestApi() {
    }

    class MyWebChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
        }
    }

    class MyWebViewClient : WebViewClient() {

    }

}
