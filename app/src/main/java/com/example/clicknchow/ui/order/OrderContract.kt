package com.example.clicknchow.ui.order

import com.example.clicknchow.base.BasePresenter
import com.example.clicknchow.base.BaseView
import com.example.clicknchow.model.response.transaction.TransactionResponse

interface OrderContract {

    interface View: BaseView {
        fun onTransactionSuccess(transactionResponse: TransactionResponse)
        fun onTransactionFailed(message: String)
    }

    interface Presenter: OrderContract, BasePresenter {
        fun getTransaction()
    }

}