package com.midai.kotlinapplication.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject
import java.util.ArrayList
import java.util.HashMap

object GsonUtils {
    var gson: Gson? = Gson()

    /**
     * 说明：如果解析抛异常返回null
     *
     * @param result 要解析的json字符串
     * @param clazz  对应的javabean的字节码
     * @return 返回 对应的javabean 对象
     */
    fun <T> fromJson(result: String, clazz: Class<T>): T? {
        try {
            if (gson == null) {
                gson = Gson()
            }
            return gson!!.fromJson(result, clazz)
        } catch (e: Exception) {

            return null
        }

    }

    fun toJson(obj: Any): String {
        if (null == gson) {
            gson = Gson()
        }
        return gson!!.toJson(obj)
    }

//    fun <T> fromJsonList(result: String, clazz: Class<T>): List<T>? {
//        try {
//            if (gson == null) {
//                gson = Gson()
//            }
//            return gson!!.fromJson<List<T>>(result, object : TypeToken<List<GoodsDetailsSpecificationsBean>>() {
//
//            }.type)
//        } catch (e: Exception) {
//
//            return null
//        }
//
//    }

    /**
     *      * 以"["开头的String转list,list里装有map
     *     
     */
    @Throws(Exception::class)
    fun jsonStr2List(jsonStr: String): List<Map<String, Any>> {
        val list = ArrayList<Map<String, Any>>()
        val ja = JSONArray(jsonStr)
        for (j in 0 until ja.length()) {
            val jm = ja.getString(j)
            if (jm.indexOf("{") == 0) {
                val m = jsonStr2Map(jm)
                list.add(m)
            }
        }
        return list
    }

    @Throws(Exception::class)
    fun jsonStr2Map(jsonStr: String): Map<String, Any> {
        val map = HashMap<String, Any>()
        if (!jsonStr.isEmpty()) {
            val json = JSONObject(jsonStr)
            val i = json.keys()
            while (i.hasNext()) {
                val key = i.next() as String
                val value = json.getString(key)
                if (value.indexOf("{") == 0) {
                    map[key.trim { it <= ' ' }] = jsonStr2Map(value)
                } else if (value.indexOf("[") == 0) {
                    map[key.trim { it <= ' ' }] = jsonStr2List(value)
                } else {
                    map[key.trim { it <= ' ' }] = value.trim { it <= ' ' }
                }
            }
        }
        return map
    }
}