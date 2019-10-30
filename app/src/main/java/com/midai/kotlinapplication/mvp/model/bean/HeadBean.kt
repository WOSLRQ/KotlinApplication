package com.midai.kotlinapplication.mvp.model.bean

data class HeadBean(
    val `data`: HeadData,
    val message: String,
    val status: Int
)

data class HeadData(
    val pic: String
)