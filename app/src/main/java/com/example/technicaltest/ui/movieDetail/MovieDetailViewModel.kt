package com.example.technicaltest.ui.movieDetail

import android.util.Log
import com.example.domain.MovieRepository
import com.example.domain.entity.MovieDetailEntity
import com.example.technicaltest.core.MovieViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel@Inject constructor(
    private val movieRepository: MovieRepository,
) : MovieViewModel<MovieDetailState>(MovieDetailState()) {
    override val viewStateCopy get() = viewState.copy()

    fun onUiEvent(uiEvent: MovieDetailViewUiEvent){
        when(uiEvent){
            is MovieDetailViewUiEvent.OnMovieDetailLoaded -> handleMovieDetailLoaded(uiEvent)
            else -> {}
        }
    }

    private fun handleMovieDetailLoaded(uiEvent: MovieDetailViewUiEvent.OnMovieDetailLoaded) = launch {
        reduce {
            requestInProgress = true
        }
        movieRepository.getMovieDetails(uiEvent.id)
            .onSuccess { response ->
                reduce {
                    result = response.toModel()
                    insertMovieDetail(response)
                }
                Log.d("---TAG----","Movie Detail Success")
            }
            .onFailure {
                Log.d("---TAG----","Movie Detail Failed")
            }
        reduce {
            requestInProgress = false
        }
    }

    private fun insertMovieDetail(movieDetailEntity: MovieDetailEntity) = launch{
       // movieRepository.insertMovieDetail(movieDetailEntity)
    }
}