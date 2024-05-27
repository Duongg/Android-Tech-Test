package com.example.technicaltest.ui.movieList

import com.example.technicaltest.core.MovieState

data class MoviesListViewState(
    var searchInput: String? = null,
    var isTrendingMovie: Boolean = false,
    var results: List<MovieItemModel>? = null,
    override var requestInProgress: Boolean = false,
): MovieState()