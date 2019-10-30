package com.midai.kotlinapplication.mvp.model.bean

import java.util.*

data class UserBean(
    val `data`: Objects,
    val message: String,
    val status: Int
)
data class Objects(
    val invite_code: String,
    val nickname: String,
    val openid: String,
    val phone: String,
    val pic: String,
    val pwd: Int,
    val total_time: Int,
    val users_id: Int
)