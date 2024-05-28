package com.example.technicaltest.ui.movieDetail

import androidx.activity.compose.setContent
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.technicaltest.MainActivity
import com.example.technicaltest.TAG_ITEM_MOVIE
import com.example.technicaltest.TAG_LOADING
import com.example.technicaltest.TAG_MOVIE_DETAIL_DATE
import com.example.technicaltest.TAG_MOVIE_DETAIL_IMAGE
import com.example.technicaltest.TAG_MOVIE_DETAIL_OVERVIEW
import com.example.technicaltest.TAG_MOVIE_DETAIL_POPULARITY
import com.example.technicaltest.TAG_MOVIE_DETAIL_PRODUCTION_COUNTRY
import com.example.technicaltest.TAG_MOVIE_DETAIL_STATUS
import com.example.technicaltest.TAG_MOVIE_DETAIL_TITLE
import com.example.technicaltest.TAG_MOVIE_DETAIL_VOTE
import com.example.technicaltest.TAG_MOVIE_DETAIL_VOTE_AVERAGE
import com.example.technicaltest.TAG_MOVIE_TITLE_DETAIL
import com.example.technicaltest.TAG_SEARCH_BUTTON
import com.example.technicaltest.navigation.MovieRoutes
import com.example.technicaltest.ui.movieList.MovieListView
import com.example.technicaltest.ui.theme.TechnicalTestTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class MovieDetailViewTest {
    @get:Rule
    val composeRule = createAndroidComposeRule(MainActivity::class.java)
    private lateinit var navController: NavHostController

    @Before
    fun setUp() {
        composeRule.activity.setContent {
            TechnicalTestTheme {
                navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = MovieRoutes.MovieListScreen.route
                ) {
                    composable(route = MovieRoutes.MovieListScreen.route) {
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
                    ) {
                        val id = it.arguments?.getInt("id") ?: -1
                        MovieDetailView(navController, id)
                    }
                }
            }
        }
    }

    @Test
    fun shouldMovieDetailShowAllInformation() {
        val searchButton = composeRule.onNodeWithTag(TAG_SEARCH_BUTTON, useUnmergedTree = true)
        searchButton.assertIsDisplayed()
        searchButton.performClick()
        composeRule.waitUntilDoesNotExist(hasTestTag(TAG_LOADING), 10000)

        val itemMovie = composeRule.onAllNodesWithTag(TAG_ITEM_MOVIE, useUnmergedTree = true)
        itemMovie[0].performClick()
        composeRule.waitUntilDoesNotExist(hasTestTag(TAG_LOADING), 10000)

        val titleScreen = composeRule.onNodeWithTag(TAG_MOVIE_TITLE_DETAIL, useUnmergedTree = true)
        titleScreen.assertIsDisplayed()

        val titleMovie = composeRule.onNodeWithTag(TAG_MOVIE_DETAIL_TITLE, useUnmergedTree = true)
        titleMovie.assertIsDisplayed()

        val image = composeRule.onNodeWithTag(TAG_MOVIE_DETAIL_IMAGE, useUnmergedTree = true)
        image.assertIsDisplayed()

        val date = composeRule.onNodeWithTag(TAG_MOVIE_DETAIL_DATE, useUnmergedTree = true)
        date.assertIsDisplayed()

        val voteAverage =
            composeRule.onNodeWithTag(TAG_MOVIE_DETAIL_VOTE_AVERAGE, useUnmergedTree = true)
        voteAverage.assertIsDisplayed()

        val status = composeRule.onNodeWithTag(TAG_MOVIE_DETAIL_STATUS, useUnmergedTree = true)
        status.assertIsDisplayed()

        val budget = composeRule.onNodeWithTag(TAG_MOVIE_DETAIL_STATUS, useUnmergedTree = true)
        budget.assertIsDisplayed()

        val vote = composeRule.onNodeWithTag(TAG_MOVIE_DETAIL_VOTE)
        vote.assertIsDisplayed()

        val popularity = composeRule.onNodeWithTag(TAG_MOVIE_DETAIL_POPULARITY)
        popularity.assertIsDisplayed()

        val overview = composeRule.onNodeWithTag(TAG_MOVIE_DETAIL_OVERVIEW)
        overview.assertIsDisplayed()

        val production = composeRule.onNodeWithTag(TAG_MOVIE_DETAIL_PRODUCTION_COUNTRY)
        production.assertIsDisplayed()
    }
}