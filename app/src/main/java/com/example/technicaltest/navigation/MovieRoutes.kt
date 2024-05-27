package com.example.technicaltest.navigation

sealed class MovieRoutes(val route: String) {
    object MovieListScreen: MovieRoutes("movie_list_screen")

    object MovieDetailScreen: MovieRoutes("movie_detail_screen")

}