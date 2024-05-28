package com.example.technicaltest.core


abstract class MovieState {
    abstract var errorMessage: Event<MovieErrorModel>?
    abstract var requestInProgress: Boolean
}

data class MovieErrorModel(
    val title: String? = null,
    val message: String? = null,
)