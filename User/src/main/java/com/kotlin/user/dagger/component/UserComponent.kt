package com.kotlin.user.dagger.component

import com.kotlin.base.dagger.component.BaseActivityComponent
import com.kotlin.user.dagger.module.UploadImageModule
import com.kotlin.user.dagger.module.UserModule
import com.kotlin.user.mvp.activity.*
import dagger.Component

/**
 * Created by  on 2018/5/7.
 */
@Component(dependencies = arrayOf(BaseActivityComponent::class),modules = arrayOf(UserModule::class,UploadImageModule::class) )
interface UserComponent {
    fun inject(registerActivity: RegisterActivity)
    fun inject(loginActivity: LoginActivity)
    fun inject(loginActivity: ForgetPwdActivity)
    fun inject(resetPwdActivity: ResetPwdActivity)
    fun inject(userInfoActivity: UserInfoActivity)
}