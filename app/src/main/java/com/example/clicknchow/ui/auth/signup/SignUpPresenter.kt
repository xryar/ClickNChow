package com.example.clicknchow.ui.auth.signup

import android.net.Uri
import android.view.View
import com.example.clicknchow.model.request.RegisterRequest
import com.example.clicknchow.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class SignUpPresenter(private val view: SignUpContract.View): SignUpContract.Presenter {

    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun submitRegister(registerRequest: RegisterRequest, viewParms: View) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.register(
            registerRequest.name,
            registerRequest.email,
            registerRequest.password,
            registerRequest.password_confirmation,
            registerRequest.address,
            registerRequest.city,
            registerRequest.houseNumber,
            registerRequest.phoneNumber,
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                view.dismissLoading()
                if (it.meta?.status.equals("success", true)) {
                    it.data?.let { it1 -> view.onRegisterSuccess(it1, viewParms) }
                } else {
                    view.onRegisterFailed(it.meta?.message.toString())
                }
            }, { throwable ->
                view.dismissLoading()
                throwable.printStackTrace()
                view.onRegisterFailed("Register failed: ${throwable.message}")
            })
        mCompositeDisposable.add(disposable)
    }

    override fun submitPhotoRegister(filePath: Uri, viewParms: View) {
        view.showLoading()

        val profileImageFile = File(filePath.path)
        val profileImageRequestBody = profileImageFile.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val profileImageParms = MultipartBody.Part.createFormData(
            "file",
            profileImageFile.name,
            profileImageRequestBody
        )

        val disposable = HttpClient.getInstance().getApi()!!.registerPhoto(profileImageParms)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                view.dismissLoading()
                if (it.meta?.status.equals("success", true)) {
                    it.data?.let { it1 -> view.onRegisterPhotoSuccess(viewParms) }
                } else {
                    view.onRegisterFailed(it.meta?.message.toString())
                }
            }, { throwable ->
                view.dismissLoading()
                throwable.printStackTrace()
                view.onRegisterFailed("Register failed: ${throwable.message}")
            })
        mCompositeDisposable.add(disposable)
    }

    override fun subscribe() {

    }

    override fun unSubscribe() {
        mCompositeDisposable.clear()
    }
}