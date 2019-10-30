package com.midai.kotlinapplication.mvp.contarct

import com.midai.kotlinapplication.mvp.model.bean.HeadBean
import com.midai.kotlinapplication.mvp.model.bean.UserBean
 interface LoginContract {
    interface Model{
        fun getPresenter(presenter: Presenter)//关联Presenter，Model

        //发送请求
        fun SendLoginRequest(loginJson:String,headJson:String)
    }
    interface Presenter{
        fun getView(view: View)//关联Presenter，view

        //发送请求
        fun Login(strPhone:String,strPwd:String)//登录
        fun getHead(strUid:Int)//获取头像
        fun SendRequest()//发送网络请求
        //回调结果
        fun LoginSuccess(userBean: UserBean)//登录成功
        fun LoginFault(message:String)//登录失败

        fun getHeadSuccess(headBean: HeadBean)//获取头像成功
        fun getHeadFault(message:String)//获取头像失败
    }
    interface View{
        //回调结果
        fun LoginSuccess(userBean: UserBean)//登录成功
        fun LoginFault(message:String)//登录失败

        fun getHeadSuccess(headBean: HeadBean)//获取头像成功
        fun getHeadFault(message:String)//获取头像失败
    }
}