package com.example.technicaltest.ui.movieList

sealed class MoviesListViewUiEvent {
    class SearchClick(val searchInput: String): MoviesListViewUiEvent()
    class SearchChanged(val searchInput: String): MoviesListViewUiEvent()
    data class ItemScrolled(val index: Int): MoviesListViewUiEvent()
    class OnOKClicked(): MoviesListViewUiEvent()
}