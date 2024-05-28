package com.example.technicaltest.ui.movieDetail

import com.example.technicaltest.core.Event
import com.example.technicaltest.core.MovieErrorModel
import com.example.technicaltest.core.MovieState

data class MovieDetailState(
    var result: MovieDetailModel? = null,
    override var requestInProgress: Boolean = false,
    override var errorMessage: Event<MovieErrorModel>? = null
) : MovieState() {
}