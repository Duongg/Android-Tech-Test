package com.example.domain

import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieRepository: MovieRepository,
    dispatcher: CoroutineDispatcher
) : CoroutineAware(dispatcher) {
}