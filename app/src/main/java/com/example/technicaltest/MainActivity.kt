package com.example.technicaltest

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.technicaltest.navigation.MovieRoutes
import com.example.technicaltest.ui.movieDetail.MovieDetailView
import com.example.technicaltest.ui.movieList.MovieListView
import com.example.technicaltest.ui.theme.TechnicalTestTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieApplication: Application()
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechnicalTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = MovieRoutes.MovieListScreen.route
                    ) {
                        composable(route = MovieRoutes.MovieListScreen.route){
                            MovieListView(navController = navController)
                        }
                        composable(
                            route = MovieRoutes.MovieDetailScreen.route + "?id={id}",
                            arguments = listOf(
                                navArgument(
                                    name = "id"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ){
                            val id = it.arguments?.getInt("id") ?: -1
                            MovieDetailView(navController, id)
                        }
                    }

                }
            }
        }
    }
}
