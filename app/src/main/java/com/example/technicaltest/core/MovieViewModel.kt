package com.example.technicaltest.core

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.datasource.MovieException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class MovieViewModel<STATE: MovieState>(
    defaultState: STATE
): ViewModel() {

    val viewState: STATE get() = _viewState.value
    private val _viewState = mutableStateOf(defaultState)

    abstract val viewStateCopy: STATE


    protected fun <T> launch(
        dispatcher: CoroutineDispatcher = Dispatchers.Main,
        block: suspend () -> T,
    ): Job {
        return viewModelScope.launch(dispatcher){
            block()
        }
    }
    fun reduce(block: STATE.() -> Unit){
        _viewState.value = viewStateCopy.apply(block)
    }

    protected fun handleError(throwable: Throwable, block: (() -> MovieErrorModel?)? = null){
        val handleError = block?.invoke() ?: when(throwable){
            is MovieException.ResponseFailed -> {MovieErrorModel("Something went wrong","Please try again")}
            is MovieException.AuthenticationFailed -> {MovieErrorModel("Authentication Failed", "Please try again")}
            is MovieException.LostInternetConnection -> {MovieErrorModel("Internet Connection Error","Please try again")}
            else -> {MovieErrorModel("Something went wrong","Please try again")}
        }
        reduce {
            errorMessage = Event(handleError)
        }
    }
}

data class Event<out T>(
    private val content: T,
    private var hasBeenHandled: Boolean = false
){
    fun getContent(): T? {
        return if(hasBeenHandled){
            null
        }else{
            hasBeenHandled = true
            content
        }
    }
}