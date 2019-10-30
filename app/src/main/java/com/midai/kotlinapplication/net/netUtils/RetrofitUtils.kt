package com.midai.kotlinapplication.net.netUtils

import com.midai.kotlinapplication.net.netapi.ApiService
import com.midai.kotlinapplication.net.netapi.URLConstant
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitUtils {
    //初始化Retrofit
    var apiService = intiRetrofit()
    fun intiRetrofit():ApiService{
        apiService = Retrofit.Builder()
            .client(OkhttpUtils.okhttpUnitls())
            .baseUrl(URLConstant.SENDR_EQUEST_URL)
            .addConverterFactory(ScalarsConverterFactory.create())//增加返回值为String的支持
                /*如果使用GsonConverterFactory时，onNext会对返回的json自动解析为实体类，但是
                如果返回的json数据如果不能保证一致，会导致错误，不会操作，所以先返回json字符串
                在根据情况去处理数据
                */
//            .addConverterFactory(GsonConverterFactory.create())//增加返回值为Gson的支持
//            .addConverterFactory(JsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ApiService::class.java)
        return apiService
    }

}