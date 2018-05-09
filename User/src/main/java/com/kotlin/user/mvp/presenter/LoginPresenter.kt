package com.kotlin.user.mvp.presenter

import com.kotlin.base.Rx.BaseRxObserver
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.user.mvp.model.response.UserLoginResponse
import com.kotlin.user.mvp.model.server.UserServer
import com.kotlin.user.mvp.model.server.UserServerImpl
import com.kotlin.user.mvp.presenter.view.LoginView
import javax.inject.Inject

/**
 * Created by  on 2018/5/5.
 */
class LoginPresenter @Inject constructor() :BasePresenter<LoginView>() {




    @Inject
    lateinit var mUserServer: UserServer

    fun login(email:String, pwd:String,pushId:String){




        mUserServer.login(email,pwd,pushId)
                .excute(object : BaseRxObserver<BaseResponse<UserLoginResponse>>(mView){
                    override fun success(data: BaseResponse<UserLoginResponse>) {

                        mView.onLoginSuccess(data.data)

                    }

                    override fun failure(statusCode: Int, msg: String?) {
                        mView.onError(statusCode,msg)

                    }



                },rxLifecycle)

    }

}