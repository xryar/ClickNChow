package com.example.clicknchow.network

import android.util.Log
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.clicknchow.BuildConfig
import com.example.clicknchow.ClickNChow
import com.example.clicknchow.utils.Helpers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HttpClient {

    private var client: Retrofit? = null
    private var endPoint: Endpoint? = null

    companion object {
        private val mInstance: HttpClient = HttpClient()
        @Synchronized
        fun getInstance():HttpClient {
            return mInstance
        }
    }

    init {
        buildRetrofitClient()
    }

    private fun buildRetrofitClient() {
        val token = ClickNChow.getApp().getToken()
        buildRetrofitClient(token)
    }

    fun buildRetrofitClient(token: String?) {
        val builder = OkHttpClient.Builder()
            builder.connectTimeout(10, TimeUnit.SECONDS)
            builder.readTimeout(10, TimeUnit.SECONDS)
            builder.writeTimeout(10, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
            builder.addInterceptor(ChuckerInterceptor(ClickNChow.getApp()))
        }

        if (token != null) {
            builder.addInterceptor(getInterceptorWithHeader("Authorization", "Bearer $token"))
        }

        val okHttpClient = builder.build()
        client = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(Helpers.getDefaultGson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        endPoint = null

        Log.v("Header", "Token : $token")
    }

    private fun getInterceptorWithHeader(headerName: String, headerValue: String): Interceptor {
        val header = HashMap<String, String>()
        header[headerName] = headerValue
        return getInterceptorWithHeader(header)
    }

    private fun getInterceptorWithHeader(headers: Map<String, String>): Interceptor {
        return Interceptor {
            val original = it.request()
            val builder = original.newBuilder()
            for ((key, value ) in headers) {
                builder.addHeader(key, value)
            }
            builder.method(original.method, original.body)
            it.proceed(builder.build())
        }
    }

    fun getApi(): Endpoint? {
        if (endPoint == null) {
            endPoint = client!!.create(Endpoint::class.java)
        }
        return endPoint
    }
}