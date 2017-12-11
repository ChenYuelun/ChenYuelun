package com.chen.librarynetwork.transformer

import com.chen.librarynetwork.handleException
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
class MyErrorTransformer<T> private constructor() : ObservableTransformer<T, T> {
    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream.map(object : Function<T, T> {
            override fun apply(t: T): T {
                // 通过对返回码进行业务判断决定是返回错误还是正常取数据
                //                if (httpResult.getCode() != ErrorType.SUCCESS){
                //                    throw new ServerException(httpResult.getMsg(), httpResult.getCode());
                //                }
                return t
            }
        }).onErrorResumeNext(object : Function<Throwable, Observable<out T>> {
            override fun apply(t: Throwable): Observable<out T> {
                t.printStackTrace()
                return Observable.error(handleException(t))
            }

            fun call(throwable: Throwable): Observable<out T> {
                //ExceptionEngine为处理异常的驱动器
                throwable.printStackTrace()
                return Observable.error(handleException(throwable))
            }
        })
    }

    //单例

    class Singleton <T> {

        private @Volatile var instance: MyErrorTransformer<T>? = null

         fun get(): MyErrorTransformer<T>? {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = MyErrorTransformer<T>()
                    }
                }
            }
            return instance!!
        }
    }


}