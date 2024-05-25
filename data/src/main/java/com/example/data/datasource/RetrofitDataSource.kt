package com.example.data.datasource

import com.example.domain.CoroutineAware
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Response

abstract class RetrofitDataSource(
    coroutineDispatcher: CoroutineDispatcher
): CoroutineAware(coroutineDispatcher) {
    suspend fun <T> apiCall(call: suspend () -> Response<T>): Result<T>{
        return try {
            val response = call.invoke()
            if(response.isSuccessful){
                Result.success((response.body())!!)
            }else{
                Result.failure(Throwable())
            }
        }catch (t: Throwable){
            Result.failure(t)
        }
    }
}