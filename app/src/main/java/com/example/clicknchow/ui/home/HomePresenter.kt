package com.example.clicknchow.ui.home

import com.example.clicknchow.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomePresenter(private val view: HomeContract.View): HomeContract.Presenter {

    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun getHome() {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.home()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                view.dismissLoading()
                if (it.meta?.status.equals("success", true)) {
                    it.data?.let { it1 -> view.onHomeSuccess(it1) }
                } else {
                    it.meta?.let { it1 -> view.onHomeFailed(it1.message) }
                }
            }, { throwable ->
                view.dismissLoading()
                throwable.printStackTrace()
                view.onHomeFailed("failed: ${throwable.message}")
            })
        view.dismissLoading()
        mCompositeDisposable.add(disposable)
    }

    override fun subscribe() {

    }

    override fun unSubscribe() {
        mCompositeDisposable.clear()
    }
}