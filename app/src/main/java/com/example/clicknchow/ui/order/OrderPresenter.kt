package com.example.clicknchow.ui.order

import com.example.clicknchow.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class OrderPresenter(private val view: OrderContract.View): OrderContract.Presenter {

    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun getTransaction() {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.transaction()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                view.dismissLoading()
                if (it.meta?.status.equals("success", true)) {
                    it.data?.let { it1 -> view.onTransactionSuccess(it1) }
                } else {
                    it.meta?.let { it1 -> view.onTransactionFailed(it1.message) }
                }
            }, { throwable ->
                view.dismissLoading()
                throwable.printStackTrace()
                view.onTransactionFailed("Login failed: ${throwable.message}")
            })
        mCompositeDisposable.add(disposable)
    }

    override fun subscribe() {

    }

    override fun unSubscribe() {
        mCompositeDisposable.clear()
    }
}