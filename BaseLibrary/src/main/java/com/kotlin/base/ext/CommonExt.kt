package com.kotlin.base.ext

import com.kotlin.base.Rx.BaseRxObserver
import com.kotlin.base.data.protocol.BaseResponse
import com.trello.rxlifecycle2.LifecycleProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers



/**
 * Created by  on 2018/5/4.
 */

 fun <T : BaseResponse<*>> Observable<T>.excute(subscribe: BaseRxObserver<T>,rxLifecycle: LifecycleProvider<*>){

         this.subscribeOn(Schedulers.newThread())
                 .compose(rxLifecycle.bindToLifecycle())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(subscribe);
}
