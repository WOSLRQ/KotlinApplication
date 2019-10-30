package com.midai.kotlinapplication.net.netUtils

import okhttp3.MediaType
import okhttp3.RequestBody

object RequestBodyUntils {
    fun JsonToRequestBody(json:String):RequestBody{
        //将请求的参数json转化为RequestBody
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json)
    }
}