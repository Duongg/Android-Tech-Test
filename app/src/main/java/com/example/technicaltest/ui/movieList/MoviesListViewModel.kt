package com.example.technicaltest.ui.movieList

import android.util.Log
import com.example.domain.MovieRepository
import com.example.technicaltest.core.MovieViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : MovieViewModel<MoviesListViewState>(MoviesListViewState()){
    override val viewStateCopy get() = viewState.copy()

     fun onUiEvent(uiEvent: MoviesListViewUiEvent){
        when(uiEvent){
            is MoviesListViewUiEvent.SearchClick -> handleSearchClick(uiEvent)
            is MoviesListViewUiEvent.SearchChanged -> handleSearchChanged(uiEvent.searchInput)
            is MoviesListViewUiEvent.OnMovieItemClicked -> handleMovieItemClicked(uiEvent)
        }
    }

    private fun handleMovieItemClicked(uiEvent: MoviesListViewUiEvent.OnMovieItemClicked) {

    }

    private fun handleSearchChanged(search: String) {
        reduce {
            searchInput = search
        }
    }
    private fun handleSearchClick(uiEvent: MoviesListViewUiEvent.SearchClick) {
        reduce {
            searchInput = uiEvent.searchInput
        }
        if (viewState.searchInput.isNullOrEmpty()) {
            reduce {
                isTrendingMovie = true
            }
            getMovieTrendingList()

        } else {
            reduce {
                isTrendingMovie = false
            }
            getMoviesListResult(uiEvent)
        }
    }
    private fun getMovieTrendingList() = launch {
        // get movies list with search input is empty
        reduce {
            requestInProgress = true
        }
        movieRepository.getTrendingMoviesList()
            .onSuccess { response ->
                reduce {
                    results = response.results?.map { it.toModel() }
                }
                Log.d("---TAG----","Movie Trending Success")
            }
            .onFailure { Log.d("---TAG----","Movie Trending Failed") }
        reduce {
            requestInProgress = false
        }
    }
    private fun getMoviesListResult(uiEvent: MoviesListViewUiEvent.SearchClick) = launch {
        // get movies list with search input is not empty
        reduce {
            searchInput = uiEvent.searchInput
        }
        reduce {
            requestInProgress = true
        }
        viewState.searchInput?.let {
            movieRepository.getMovieListBySearchInput(it)
                .onSuccess {response ->
                    reduce {
                        results = response.results?.map { it.toModel() }
                    }
                    Log.d("---TAG----","Movie List Success")
                }
                .onFailure {  Log.d("---TAG----","Movie List Failed") }
        }
        reduce {
            requestInProgress = false
        }
    }
}