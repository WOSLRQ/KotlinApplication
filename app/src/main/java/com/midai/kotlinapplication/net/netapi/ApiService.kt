package com.midai.kotlinapplication.net.netapi

import io.reactivex.Observable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiService {
    //管理所有的请求
    //登录
    @POST("android/v1/login/index")
    fun login(@Body body: RequestBody) : Observable<ResponseBody>
    //头像
    @POST("together/v1/me/img")
    fun getHead(@Body body: RequestBody) : Observable<ResponseBody>
}