package com.example.clicknchow.ui.auth.signin

import com.example.clicknchow.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SignInPresenter(private val view: SignContract.View): SignContract.Presenter {

    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun submitLogin(email: String, password: String) {
        val disposable = HttpClient.getInstance().getApi()!!.login(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                if (it.meta?.status.equals("success", true)) {
                    it.data?.let { it1 -> view.onLoginSuccess(it1) }
                } else {
                    it.meta?.let { it1 -> view.onLoginFailed(it1.message) }
                }
            }, { throwable ->
                throwable.printStackTrace()
                view.onLoginFailed("Login failed: ${throwable.message}")
            })
        mCompositeDisposable.add(disposable)
    }

    override fun subscribe() {

    }

    override fun unSubscribe() {
        mCompositeDisposable.clear()
    }
}