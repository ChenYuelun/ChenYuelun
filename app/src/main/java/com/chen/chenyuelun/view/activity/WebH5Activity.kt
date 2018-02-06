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

        //webView 相关设置
        val mWebViewSettings = mWebView.getSettings()

        //在安卓5.0之后，默认不允许加载http与https混合内容，需要设置webview允许其加载混合网络协议内容
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            mWebViewSettings.setMixedContentMode(WebSettings.)
        }

        mWebViewSettings.setJavaScriptEnabled(true)
        mWebViewSettings.setBlockNetworkImage(false);//解决图片不显示
        mWebViewSettings.setUseWideViewPort(true)//设置此属性，可任意比例缩放
        mWebViewSettings.setLoadWithOverviewMode(true)
        mWebViewSettings.setRenderPriority(WebSettings.RenderPriority.HIGH)
//        mWebView.addJavascriptInterface(WebViewShareInterface(this), "shareAndroid")
        mWebViewSettings.setCacheMode(WebSettings.LOAD_NO_CACHE)  //设置 缓存模式
        //支持localStorage
        mWebViewSettings.setDomStorageEnabled(true)// 开启 DOM storage API 功能
        mWebViewSettings.setAppCacheMaxSize((1024 * 1024 * 8).toLong())
        val appCachePath = applicationContext.cacheDir.absolutePath
        mWebViewSettings.setAppCachePath(appCachePath)
        mWebViewSettings.setAllowFileAccess(true)//设置可以访问文件
        mWebViewSettings.setAppCacheEnabled(true)
        mWebViewSettings.setBlockNetworkImage(true)
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
        mWebViewSettings.setDatabaseEnabled(true)//开启 database storage API 功能
        val dbPath = getDir("database", Context.MODE_PRIVATE).path
        mWebViewSettings.setDatabasePath(dbPath)//设置  Application Caches 缓存目录
//        mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
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
