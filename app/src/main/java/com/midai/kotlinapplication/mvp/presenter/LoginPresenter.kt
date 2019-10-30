package com.midai.kotlinapplication.mvp.presenter

import com.midai.kotlinapplication.mvp.contarct.LoginContract
import com.midai.kotlinapplication.mvp.model.LoginModel
import com.midai.kotlinapplication.mvp.model.bean.HeadBean
import com.midai.kotlinapplication.mvp.model.bean.UserBean
import java.lang.reflect.Array

class LoginPresenter : LoginContract.Presenter {
    var view:LoginContract.View? = null
    var loginModel:LoginModel? = null
    var loginJson :String=""
    var headJson:String=""

    //关联Model Presenter View
    override fun getView(view: LoginContract.View) {
        this.view = view
        loginModel = LoginModel()
        loginModel!!.getPresenter(this)
    }
    //登录
    override fun Login(strPhone:String,strPwd:String) {
        //拼接登录参数json
        loginJson = "{\"phone\":\""+strPhone+"\",\"pwd\":\""+strPwd+"\"}"
    }
    //头像
    override fun getHead(strUid:Int) {
        //拼接头像参数json
        headJson = "{\"uid\":"+strUid+"}"
    }
    //发送串行请求
    override fun SendRequest() {
        //合并登录请求以及头像请求，可自由决定进行几个请求
        loginModel!!.SendLoginRequest(loginJson,headJson)
    }

    /*
    * 回调成功
    * */
    //登录成功
    override fun LoginSuccess(userBean: UserBean) {
        view!!.LoginSuccess(userBean)
    }
    //登录失败
    override fun LoginFault(message:String) {
       view!!.LoginFault(message)
    }
    //获取头像成功
    override fun getHeadSuccess(headBean: HeadBean) {
        view!!.getHeadSuccess(headBean)
    }
    //获取头像失败
    override fun getHeadFault(message:String) {
        view!!.getHeadFault(message)
    }

}