package com.example.technicaltest.ui.movieList

import android.content.Context
import android.util.Log
import com.example.data.datasource.MovieException
import com.example.domain.MovieRepository
import com.example.domain.entity.MovieItemEntity
import com.example.technicaltest.core.Event
import com.example.technicaltest.core.MovieViewModel
import com.example.technicaltest.isNetworkConnected
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val context: Context,
    private val movieRepository: MovieRepository,
) : MovieViewModel<MoviesListViewState>(MoviesListViewState()){
    override val viewStateCopy get() = viewState.copy()
    private var nextPage = 1
    private var lastScrolledItem = -1
    private var paginationJob: Job? = null

     fun onUiEvent(uiEvent: MoviesListViewUiEvent){
        when(uiEvent){
            is MoviesListViewUiEvent.SearchClick -> handleSearchClick(uiEvent)
            is MoviesListViewUiEvent.SearchChanged -> handleSearchChanged(uiEvent.searchInput)
            is MoviesListViewUiEvent.OnMovieItemClicked -> handleMovieItemClicked(uiEvent)
            is MoviesListViewUiEvent.ItemScrolled -> handleItemScroll(uiEvent.index)
            is MoviesListViewUiEvent.OnOKClicked -> handleOkClicked()
        }
    }

    private fun handleOkClicked() {
        if (viewState.isTrendingMovie) {
            getAllMovieTrendingFromDB()
        }
    }

    private fun handleItemScroll(index: Int) = launch{
        if(lastScrolledItem >= index) return@launch
        lastScrolledItem = index
        var thresHold = (viewState.results?.size ?: 0) - 10
        if(thresHold <= lastScrolledItem){
            if(paginationJob == null){
                paginationJob = getMovieTrendingList()
            }
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
            if(isNetworkConnected(context = context)){
                getMovieTrendingList()
            }else{
                handleError(MovieException.LostInternetConnection)
            }
        } else {
            reduce {
                isTrendingMovie = false
            }
            if(isNetworkConnected(context = context)){
                getMoviesListResult(uiEvent)
            }else{
                handleError(MovieException.LostInternetConnection)
            }

        }
    }
    private fun getMovieTrendingList() = launch {
        // get movies list with search input is empty
        reduce {
            requestInProgress = true
        }
        movieRepository.getTrendingMoviesList(page = nextPage)
            .onSuccess { response ->
                nextPage = nextPage.plus(1)
                reduce {
                    results = response.results?.map { it.toModel() }
                    response.results?.forEach { item -> insertMovieTrending(item) }
                }
                Log.d("---TAG----","Movie Trending Success")
            }
            .onFailure {
                handleError(it)
                Log.d("---TAG----","Movie Trending Failed")
            }
        reduce {
            requestInProgress = false
        }
        paginationJob = null
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
                .onFailure {
                    handleError(it)
                    Log.d("---TAG----","Movie List Failed")
                }
        }
        reduce {
            requestInProgress = false
        }
    }

    private fun insertMovieTrending(movieItemEntity: MovieItemEntity) = launch{
        movieRepository.insertMovieTrending(movieItemEntity)
    }

    private fun getAllMovieTrendingFromDB() = launch {
       val listTrendingMovie = movieRepository.getAllMovieTrending()
        reduce {
            results = listTrendingMovie.map { it.toModel() }
        }
    }
}