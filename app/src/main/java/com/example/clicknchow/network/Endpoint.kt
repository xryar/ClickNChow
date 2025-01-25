package com.example.clicknchow.network

import com.example.clicknchow.model.response.Wrapper
import com.example.clicknchow.model.response.login.LoginResponse
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Endpoint {

    @FormUrlEncoded
    @POST
    fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Observable<Wrapper<LoginResponse>>

}