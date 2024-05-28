package com.example.technicaltest.ui.movieDetail

sealed class MovieDetailViewUiEvent {
    class OnMovieDetailLoaded(val id: Int): MovieDetailViewUiEvent()
}