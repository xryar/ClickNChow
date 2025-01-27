package com.example.clicknchow.model.response.register

import com.example.clicknchow.model.response.login.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @Expose
    @SerializedName("access_token")
    val access_token: String,
    @Expose
    @SerializedName("token_type")
    val token_type: String,
    @Expose
    @SerializedName("user")
    val user: User
)