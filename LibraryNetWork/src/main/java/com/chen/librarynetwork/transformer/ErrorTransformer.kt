package com.chen.librarynetwork.transformer

import com.chen.librarynetwork.exception.ErrorType
import com.chen.librarynetwork.exception.ServerException
import com.chen.librarynetwork.handleException
import com.chen.librarynetwork.request.HttpResult
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.functions.Function

/**
 * Created by ${ChenYuelun} on 2017/12/11.
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
class ErrorTransformer<T> private constructor() : ObservableTransformer<HttpResult<T>, T> {
    override fun apply(upstream: Observable<HttpResult<T>>): ObservableSource<T> {
        return upstream.map(object : Function<HttpResult<T>, T> {
            override fun apply(t: HttpResult<T>): T {
                if (t.code !== ErrorType.SUCCESS) {
                    throw ServerException(t.msg, t.code)
                }
                return t.resp!!
            }
        }).onErrorResumeNext(object : Function<Throwable, Observable<out T>>{
            override fun apply(t: Throwable): Observable<out T> {
                t.printStackTrace()
                return Observable.error(handleException(t))
            }
        })
    }

    class Singleton <T> {

        private @Volatile var instance: ErrorTransformer<T>? = null

        fun get(): ErrorTransformer<T>? {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = ErrorTransformer<T>()
                    }
                }
            }

            return instance!!
        }
    }

}
