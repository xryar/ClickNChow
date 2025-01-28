package com.example.clicknchow.ui.home

import com.example.clicknchow.base.BasePresenter
import com.example.clicknchow.base.BaseView
import com.example.clicknchow.model.response.home.HomeResponse

interface HomeContract {

    interface View: BaseView {
        fun onHomeSuccess(homeResponse: HomeResponse)
        fun onHomeFailed(message: String)
    }

    interface Presenter: HomeContract, BasePresenter {
        fun getHome()
    }

}