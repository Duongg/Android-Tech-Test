package com.example.data.datasource

import com.example.data.api.dto.response.ErrorResponse
import com.example.domain.CoroutineAware
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import retrofit2.Response

abstract class RetrofitDataSource(
    coroutineDispatcher: CoroutineDispatcher
): CoroutineAware(coroutineDispatcher) {
    private val errorMapper = MovieErrorMapper()
    suspend fun <T> apiCall(call: suspend () -> Response<T>): Result<T>{
        return try {
            val response = call.invoke()
            if(response.isSuccessful){
                Result.success((response.body())!!)
            }else{
                errorMapper.mapApiError(response)
                Result.failure(Throwable())
            }
        }catch (t: Throwable){
            Result.failure(t)
        }
    }

    class MovieErrorMapper{
        private val jsonParser = Json { ignoreUnknownKeys = true}
        fun mapApiError(response: Response<*>): MovieException {
            return try {
                val errorData = response.errorBody()?.byteStream()?.bufferedReader()?.readText() ?: "{}"
                val errorModel: ErrorResponse = jsonParser.decodeFromString(errorData)
                mapApiError(errorModel)
            } catch (e:Exception) {
                MovieException.UnknownException
            }
        }
        private fun mapApiError(errorResponse: ErrorResponse): MovieException{
            return when(errorResponse.statusCode){
                401 ->  MovieException.AuthenticationFailed
                500 -> MovieException.ResponseFailed
                else ->MovieException.UnknownException
            }
        }
    }
}
open class MovieException(message: String? = null): Exception(message){
    object LostInternetConnection: MovieException()

    object AuthenticationFailed: MovieException()

    object ResponseFailed: MovieException()

    object UnknownException: MovieException()
}