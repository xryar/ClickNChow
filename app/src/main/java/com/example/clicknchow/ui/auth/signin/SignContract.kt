package com.example.clicknchow.ui.auth.signin

import com.example.clicknchow.base.BasePresenter
import com.example.clicknchow.base.BaseView
import com.example.clicknchow.model.response.login.LoginResponse

interface SignContract {

    interface View: BaseView {
        fun onLoginSuccess(loginResponse: LoginResponse)
        fun onLoginFailed(message: String)
    }

    interface Presenter: SignContract, BasePresenter {
        fun submitLogin(email: String, password: String)
    }

}