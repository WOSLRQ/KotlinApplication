package com.midai.kotlinapplication.mvp.model

import android.util.Log
import com.midai.kotlinapplication.net.requestuntils.MyObserable
import com.midai.kotlinapplication.net.requestuntils.SendRequest
import com.midai.kotlinapplication.mvp.contarct.LoginContract
import com.midai.kotlinapplication.mvp.model.bean.HeadBean
import com.midai.kotlinapplication.mvp.model.bean.UserBean
import com.midai.kotlinapplication.utils.GsonUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import java.lang.Exception

class LoginModel : LoginContract.Model {

    var presenter: LoginContract.Presenter? = null
    var position:Int = 0//用于标志返回是第几个回调，并根据需要进行各接口返回数据处理
    val LOGINCODE:Int = 1//返回登录数据
    val HEADCODE:Int = 2//返回头像数据

    //关联Model Presenter
    override fun getPresenter(presenter: LoginContract.Presenter) {
        this.presenter = presenter
    }
    //发送请求
    override fun SendLoginRequest(loginJson: String, headJson: String) {
        //发送请求 SendRequest.login()用于合并登陆以及头像请求
        SendRequest.Login(loginJson, headJson)
            /*
      *回调数据，有于我使用的是串行请求，
      * 所有返回的数据是按照顺序返回，因此定义一个position记录返回的是第几个接口数据，
       */
            .subscribe(object : MyObserable<ResponseBody>() {
                override fun onNext(t: ResponseBody) {
                    position++//用于
                    when(position){
                        LOGINCODE ->callLogin(t.string())//返回的是登录的数据
                        HEADCODE ->callHead(t.string())//返回的是头像数据
                    }
                }
            })

    }
    //返回登录信息
    private fun callLogin(result: String) {
        var userBean:UserBean? = null
        /*将数据解析成实体类，
        try catch 是由于我登录成功以及失败时后台返回的数据格式会不一样，
        但是返回数据格式不一样会导致Gson解析失败而闪退
        所以要进行异常捕获
        */
        try {
            userBean = GsonUtils.fromJson(result,UserBean::class.java)
            if (userBean!!.status==4000){
                presenter!!.LoginSuccess(userBean!!)
                Log.i("userBean","登录成功")
            }else{
                presenter!!.LoginFault(userBean!!.message)
            }
        }catch (e: Exception){
            Log.i("userBean","登录失败")
            presenter!!.LoginFault("登录失败")
        }
    }
    //返回头像
    private fun callHead(result: String) {
        var headBean = GsonUtils.fromJson(result,HeadBean::class.java)
        if (headBean!!.status==4000){
            presenter!!.getHeadSuccess(headBean!!)
        }else{
            presenter!!.getHeadFault(headBean.message)
        }
    }
}