package com.kotlin.user.mvp.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.Toolbar
import android.transition.Transition
import android.transition.TransitionInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateInterpolator
import android.widget.TextView
import com.kotlin.base.dagger.component.DaggerBaseActivityComponent
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.user.R

import com.kotlin.user.dagger.component.DaggerUserComponent


import com.kotlin.user.mvp.presenter.RegisterPresenter
import com.kotlin.user.mvp.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_login.*


import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import javax.inject.Inject


class RegisterActivity : BaseMvpActivity<RegisterPresenter>(),RegisterView ,View.OnClickListener{


    override fun onClick(v: View?) {

        when(v!!.id){
            R.id.fb_toLoginView->{
                animateRevealClose()
            }
            R.id.bt_userRegister->{
                mpresenter.register(et_phone_number.text.toString(),et_password.text.toString(),et_verification_code.text.toString())

            }

        }
    }
    override fun getContentViewResId(): Int {

        return R.layout.activity_register;
    }


    override fun initView() {

        setSupportActionBar(tb_register_bar as Toolbar)
        ShowEnterAnimation()

        fb_toLoginView.setOnClickListener(this)
        bt_userRegister.setOnClickListener(this)
        tb_register_bar.find<TextView>(R.id.tv_bar_title).setText("注册")
    }
    /**
     * 注入
     */
    override fun injectComponent() {

        DaggerUserComponent.builder().baseActivityComponent(mActiviComponent).build().inject(this)
        mpresenter.mView=this
    }
    override fun onRegisterSuccess(result: String?) {

        toast("注册成功")
    }

    override fun onRegisterFailure(statusCode: Int, msg: String?) {
        toast("注册失败(${msg})")
    }



    override fun ShowEnterAnimation() {
        val transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition)
        window.sharedElementEnterTransition = transition
        transition.addListener(object :Transition.TransitionListener{
            override fun onTransitionResume(transition: Transition?) {

            }

            override fun onTransitionPause(transition: Transition?) {

            }

            override fun onTransitionCancel(transition: Transition?) {

            }

            override fun onTransitionEnd(transition: Transition?) {
                transition!!.removeListener(this)
                animateRevealShow()
            }

            override fun onTransitionStart(transition: Transition?) {
                cv_register_bg.visibility=View.GONE
            }

        })
    }

    override fun animateRevealShow() {
        val mAnimator = ViewAnimationUtils.createCircularReveal(cv_register_bg, cv_register_bg.getWidth() / 2, 0, (fb_toLoginView.getWidth() / 2).toFloat(), cv_register_bg.getWidth().toFloat())
        mAnimator.duration = 500
        mAnimator.interpolator = AccelerateInterpolator()
        mAnimator.addListener(object : AnimatorListenerAdapter() {


            override fun onAnimationStart(animation: Animator) {
                cv_register_bg.setVisibility(View.VISIBLE)
                super.onAnimationStart(animation)
            }
        })
        mAnimator.start()
    }

    override fun animateRevealClose() {
        val mAnimator = ViewAnimationUtils.createCircularReveal(cv_register_bg, cv_register_bg.getWidth() / 2, 0, cv_register_bg.getHeight().toFloat(), (fb_toLoginView.getWidth() / 2).toFloat())
        mAnimator.duration = 500
        mAnimator.interpolator = AccelerateInterpolator()
        mAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                cv_register_bg.visibility=View.INVISIBLE
                super.onAnimationEnd(animation)

                finishAfterTransition()


            }


        })
        mAnimator.start()
    }

    override fun onBackPressed() {
        animateRevealClose()
    }
}
