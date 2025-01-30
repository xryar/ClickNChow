package com.example.clicknchow.ui.detail

import android.view.View
import com.example.clicknchow.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PaymentPresenter(private val view: PaymentContract.View): PaymentContract.Presenter {

    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun getCheckout(
        foodId: String,
        userId: String,
        quantity: String,
        total: String,
        viewParms: View
    ) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.checkout(
            foodId,
            userId,
            quantity,
            total,
            "DELIVERED"
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                view.dismissLoading()
                if (it.meta?.status.equals("success", true)) {
                    it.data?.let { it1 -> view.onCheckoutSuccess(it1, viewParms) }
                } else {
                    it.meta?.let { it1 -> view.onCheckoutFailed(it1.message) }
                }
            }, { throwable ->
                view.dismissLoading()
                throwable.printStackTrace()
                view.onCheckoutFailed("Login failed: ${throwable.message}")
            })
        mCompositeDisposable.add(disposable)
    }

    override fun subscribe() {

    }

    override fun unSubscribe() {
        mCompositeDisposable.clear()
    }
}