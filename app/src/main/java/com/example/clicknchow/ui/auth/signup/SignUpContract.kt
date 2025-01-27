package com.example.clicknchow.ui.auth.signup

import android.net.Uri
import com.example.clicknchow.base.BasePresenter
import com.example.clicknchow.base.BaseView
import com.example.clicknchow.model.request.RegisterRequest
import com.example.clicknchow.model.response.register.RegisterResponse

interface SignUpContract {

    interface View: BaseView {
        fun onRegisterSuccess(registerResponse: RegisterResponse, view: android.view.View)
        fun onRegisterPhotoSuccess(view: android.view.View)
        fun onRegisterFailed(message: String)
    }

    interface Presenter: SignUpContract, BasePresenter {
        fun submitRegister(registerRequest: RegisterRequest, view: android.view.View)
        fun submitPhotoRegister(filePath: Uri, view: android.view.View)
    }

}