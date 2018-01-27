

import android.os.Build
import com.chen.chenyuelun.data.single.AppApplication
import com.chen.chenyuelun.data.network.RequestApi
import com.chen.librarynetwork.CAIQR_CLIENT_TYPE
import com.chen.librarynetwork.InterceptorResponse
import com.chen.librarynetwork.TrustAllCerts
import com.chen.libraryresouse.base.BASE_URL_A
import com.chen.libraryresouse.utils.LogUtils
import com.chen.libraryresouse.utils.PhoneParameterUtils.Companion.getAppVersionName
import com.chen.libraryresouse.utils.PhoneParameterUtils.Companion.getPhoneName

import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.security.SecureRandom
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory

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
        private val cacheFile = File(AppApplication.instance().externalCacheDir, "ChenDevCache")
        private val cache = Cache(cacheFile, (1024 * 1024 * 100).toLong())
        private val sClient = OkHttpClient.Builder().cache(cache)
                .connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .addInterceptor { chain ->
                    val request = chain.request()
                            .newBuilder()
                            .addHeader("User-Agent", "caiqr/" + getAppVersionName(AppApplication.instance()) + "(" + getPhoneName() + "/" + Build.VERSION.RELEASE + ") " + "Client/" + CAIQR_CLIENT_TYPE)
                            .addHeader("Content-Type", "application/x-www-form-urlencoded")
                            .build()
                    chain.proceed(request)
                }
                .addInterceptor(InterceptorResponse())
                .addInterceptor(
                        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
                            //打印retrofit日志
                            LogUtils.d("RetrofitLog", "retrofitBack = " + message)
                        }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .retryOnConnectionFailure(true)
                .sslSocketFactory(createSSLSocketFactory()!!, TrustAllCerts())
                .build()

        fun createSSLSocketFactory(): SSLSocketFactory? {
            var ssfFactory: SSLSocketFactory? = null

            try {
                val sc = SSLContext.getInstance("TLS")
                sc.init(null, arrayOf(TrustAllCerts()), SecureRandom())

                ssfFactory = sc.socketFactory
            } catch (e: Exception) {
            }

            return ssfFactory
        }

        fun createRxRetrofitService(): RequestApi? {
            return Retrofit
                    .Builder()
                    .client(sClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL_A)
                    .build()
                    .create(RequestApi::class.java)
        }

        fun  createRetrofitService(): RequestApi? {
            return Retrofit
                    .Builder()
                    .client(sClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL_A)
                    .build()
                    .create(RequestApi::class.java)
        }
    }
}