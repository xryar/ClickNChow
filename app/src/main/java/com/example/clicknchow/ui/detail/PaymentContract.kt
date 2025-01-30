package com.example.clicknchow.ui.detail

import android.view.View
import com.example.clicknchow.base.BasePresenter
import com.example.clicknchow.base.BaseView
import com.example.clicknchow.model.response.checkout.CheckoutResponse
import com.example.clicknchow.model.response.home.HomeResponse

interface PaymentContract {

    interface View: BaseView {
        fun onCheckoutSuccess(checkoutResponse: CheckoutResponse, view: android.view.View)
        fun onCheckoutFailed(message: String)
    }

    interface Presenter: PaymentContract, BasePresenter {
        fun getCheckout(foodId: String, userId: String, quantity: String, total: String, view: android.view.View)
    }

}