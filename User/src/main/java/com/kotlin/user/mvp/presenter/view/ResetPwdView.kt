package com.kotlin.user.mvp.presenter.view

import com.kotlin.base.presenter.view.BaseView

/**
 * Created by  on 2018/5/10.
 */
interface ResetPwdView:BaseView {
    fun onResetPwdResult(result: String?)
}