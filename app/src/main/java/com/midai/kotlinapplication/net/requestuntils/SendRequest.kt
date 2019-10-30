package com.midai.kotlinapplication.net.requestuntils

import com.midai.kotlinapplication.net.netUtils.RequestBodyUntils
import com.midai.kotlinapplication.net.netUtils.RetrofitUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody

object SendRequest {
    //登录
    fun Login(loginJson:String,headJson:String):Observable<ResponseBody>{
        //分别将登录参数json，头像参数json转化为 RequestBody
        var LoginBody = RequestBodyUntils.JsonToRequestBody(loginJson)
        var HeadBody = RequestBodyUntils.JsonToRequestBody(headJson)
        //分别调用Retrofit的API
        var loginObservable = RetrofitUtils.apiService.login(LoginBody)
        var headObservable = RetrofitUtils.apiService.getHead(HeadBody)
        return Observable.concat(loginObservable,headObservable).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())//返回串行请求,Observable.concat合并请求，并且依次将元素发出
//        return Observable.merge(loginObservable,headObservable)//Observable.merge，但是发送顺序可能会交叉，自行根据需要选择
    }
//    fun Login(strPhone:String,strPwd:String){
//        var LoginJson = "{\"phone\":\""+strPhone+"\",\"pwd\":\""+strPwd+"\"}"
//        var LoginBody: RequestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),LoginJson)
//    }
}