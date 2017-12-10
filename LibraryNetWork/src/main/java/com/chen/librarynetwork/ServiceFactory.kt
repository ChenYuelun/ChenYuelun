package com.chen.librarynetwork

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by ${ChenYuelun} on 2017/12/10.
 *
 *----------Dragon be here!----------/
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑
 * 　　　　┃　　　┃代码无BUG！
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━━━━━━
 *
 *说明：
 */
class ServiceFactory {

    companion object {
        private val DEFAULT_TIMEOUT = 0
        private var sRetrefitA: Retrofit? = null
        private var sRetrefitB: Retrofit? = null
        private var sClient: OkHttpClient? = null


//        val cacheFile = File(.getExternalCacheDir(), "ZhiBookCache")
//        val cache = Cache(cacheFile, (1024 * 1024 * 100).toLong())
//        sClient = OkHttpClient.Builder().cache(cache)
//        .connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
//        .writeTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
//        .readTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
//        .addInterceptor(
//        object : Interceptor {
//            @Throws(IOException::class)
//            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
//                val request = chain.request()
//                        .newBuilder()
//                        .addHeader("User-Agent", "caiqr/" + CommonUtils.getAppVersionName(ActivityCollector.getTopActivity()) + "(" + CommonUtils.getPhoneName() + "/" + Build.VERSION.RELEASE + ") " + "Client/" + ParamMapValue.CAIQR_CLIENT_TYPE)
//                        .addHeader("Content-Type", "application/x-www-form-urlencoded")
//                        .build()
//                return chain.proceed(request)
//            }
//        })
//        .addInterceptor(InterceptorResponse())
//        .addInterceptor(
//        HttpLoggingInterceptor(
//        object : HttpLoggingInterceptor.Logger {
//            override fun log(message: String) {
//                //打印retrofit日志
//                LogUtils.d("RetrofitLog", "retrofitBack = " + message)
//            }
//        }).setLevel(HttpLoggingInterceptor.Level.BODY))
//        .retryOnConnectionFailure(true)
//        .sslSocketFactory(createSSLSocketFactory()!!, TrustAllCerts())
//        .build()

        fun <T> createRxRetrofitService(clazz: Class<T>, endPoint: String): T {
            return Retrofit
                    .Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(endPoint)
                    .build()
                    .create(clazz)
        }

        fun <T> createRetrofitService(clazz: Class<T>, endPoint: String): T {
            return Retrofit
                    .Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(endPoint)
                    .build()
                    .create(clazz)
        }
    }
}