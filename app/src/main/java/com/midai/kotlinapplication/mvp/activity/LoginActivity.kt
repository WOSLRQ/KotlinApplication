package com.midai.kotlinapplication.mvp.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.midai.kotlinapplication.R
import com.midai.kotlinapplication.mvp.contarct.LoginContract
import com.midai.kotlinapplication.mvp.model.bean.HeadBean
import com.midai.kotlinapplication.mvp.model.bean.UserBean
import com.midai.kotlinapplication.mvp.presenter.LoginPresenter
import com.midai.kotlinapplication.net.netapi.URLConstant
import com.midai.kotlinapplication.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), View.OnClickListener, LoginContract.View {

    var strPhone = ""
    var strPwd = ""
    var uid = 92
    var presenter: LoginPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bt_Login.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.bt_Login -> Login()
        }
    }
    //登录
    private fun Login() {
        strPhone = et_Phone.text.toString()//获取手机号码
        strPwd = et_pwd.text.toString()//获取密码

        //关联view ,presenter
        presenter = LoginPresenter()
        presenter!!.getView(this)

        presenter!!.Login(strPhone, strPwd)//获取登录信息请求
        presenter!!.getHead(uid)//获取头像请求
        presenter!!.SendRequest()//将登陆请求和头像请求合并进行

    }

    //回调数据
    //登录成功
    override fun LoginSuccess(userBean: UserBean) {
        tv_Phone.text = userBean.data.phone//显示手机号码
        tv_NickName.text = userBean.data.nickname//显示昵称
    }

    //登录失败
    override fun LoginFault(message:String) {
        ToastUtils.ShowToast(this,message)
//        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    //获取头像成功
    override fun getHeadSuccess(headBean: HeadBean) {
        //显示头像
        Glide.with(this).load(URLConstant.SPLICE_URL2+headBean.data.pic).into(iv_Head)
    }

    //获取头像失败
    override fun getHeadFault(message:String) {
        ToastUtils.ShowToast(this,message)
    }
    //解绑presenter
    override fun onStop() {
        super.onStop()
        if (presenter!=null){
            presenter = null
        }
    }
}
