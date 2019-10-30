package com.midai.kotlinapplication.net.netUtils

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object OkhttpUtils {


    fun okhttpUnitls():OkHttpClient{
        var httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        //Okhttp对象
         var okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
             .addInterceptor(headerInterceptor)
            .retryOnConnectionFailure(true)//失败重连
            .connectTimeout(30, TimeUnit.SECONDS)//网络请求超时时间单位为秒
            .build()
        return okHttpClient
    }
//添加头部信息拦截器
object headerInterceptor:Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            //                        .addHeader("Accept-Encoding", "gzip")
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .method(originalRequest.method(), originalRequest.body())
        requestBuilder.addHeader("Authorization","")//添加请求头信息，服务器进行token有效性验证
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}
}