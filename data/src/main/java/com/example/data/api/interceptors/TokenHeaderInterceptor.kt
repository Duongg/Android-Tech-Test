package com.example.data.api.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenHeaderInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("accept", "application/json")
            .addHeader("Authorization", "Bearer 47aa75b56464da7a186b813a50035cd4")
            .build()
        return chain.proceed(request)
    }
}