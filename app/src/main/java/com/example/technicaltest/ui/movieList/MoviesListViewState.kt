package com.example.technicaltest.ui.movieList

import com.example.technicaltest.core.Event
import com.example.technicaltest.core.MovieErrorModel
import com.example.technicaltest.core.MovieState

data class MoviesListViewState(
    var searchInput: String? = null,
    var isTrendingMovie: Boolean = false,
    var results: List<MovieItemModel>? = null,
    override var requestInProgress: Boolean = false,
    override var errorMessage: Event<MovieErrorModel>? = null
): MovieState()