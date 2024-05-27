package com.example.technicaltest.ui.movieDetail

import com.example.technicaltest.core.MovieState

data class MovieDetailState(
    var result: MovieDetailModel? = null,
    override var requestInProgress: Boolean = false
) : MovieState() {
}