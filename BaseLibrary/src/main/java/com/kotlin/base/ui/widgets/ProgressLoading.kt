package com.kotlin.base.ui.widgets

import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.AnimationDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.kotlin.base.R
import org.jetbrains.anko.find

/**
 * Created by caofu on 2018/5/8.
 */
class ProgressLoadingBar private constructor(context: Context?, themeResId: Int) : Dialog(context, themeResId) {


    companion object {
        lateinit var mLoadingBar:ProgressLoadingBar
        lateinit var mAnimationDrawable:AnimationDrawable

        fun create(mContext:Context):ProgressLoadingBar{
            mLoadingBar= ProgressLoadingBar(mContext,R.style.LightProgressDialog)

            mLoadingBar.setContentView(R.layout.progress_dialog)



            mAnimationDrawable=mLoadingBar.find<ImageView>(R.id.iv_loading_image).background as AnimationDrawable

            val lp = mLoadingBar.window.attributes
            lp.dimAmount = 0.2f

            lp.gravity= Gravity.CENTER
            //设置属性
            mLoadingBar.window.attributes = lp

            return mLoadingBar;
        }
    }

    fun showLoadingBar(){
        mLoadingBar?.show()
        mAnimationDrawable?.start()
    }

    fun dismissLoadingBar(){
        mLoadingBar?.dismiss()
        mAnimationDrawable?.stop()
    }

}