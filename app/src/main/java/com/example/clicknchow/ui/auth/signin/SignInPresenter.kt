package com.example.clicknchow.ui.auth.signin

import io.reactivex.disposables.CompositeDisposable

class SignInPresenter(private val view: SignContract.View): SignContract.Presenter {

    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun submitLogin(email: String, password: String) {

    }

    override fun subscribe() {

    }

    override fun unSubscribe() {
        mCompositeDisposable.clear()
    }
}