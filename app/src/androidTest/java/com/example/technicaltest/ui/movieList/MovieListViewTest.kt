package com.example.technicaltest.ui.movieList

import androidx.activity.compose.setContent
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.technicaltest.MainActivity
import com.example.technicaltest.TAG_ITEM_IMAGE
import com.example.technicaltest.TAG_ITEM_RELEASE_DATE
import com.example.technicaltest.TAG_ITEM_TITLE
import com.example.technicaltest.TAG_ITEM_VOTE_AVER
import com.example.technicaltest.TAG_LIST_HEADER
import com.example.technicaltest.TAG_LIST_ITEM
import com.example.technicaltest.TAG_LOADING
import com.example.technicaltest.TAG_MOVIE_TITLE_LIST
import com.example.technicaltest.TAG_SEARCH_BUTTON
import com.example.technicaltest.TAG_SEARCH_FIELD
import com.example.technicaltest.navigation.MovieRoutes
import com.example.technicaltest.ui.theme.TechnicalTestTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class MovieListViewTest {
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
                }
            }
        }
    }

    @Test
    fun shouldMovieListShowAllInformation() {

        val movieTitle = composeRule.onNodeWithTag(TAG_MOVIE_TITLE_LIST, useUnmergedTree = true)
        movieTitle.assertIsDisplayed()

        val movieSearchView = composeRule.onNodeWithTag(TAG_SEARCH_FIELD, useUnmergedTree = true)
        movieSearchView.assertIsDisplayed()

        val searchButton = composeRule.onNodeWithTag(TAG_SEARCH_BUTTON, useUnmergedTree = true)
        searchButton.assertIsDisplayed()
        searchButton.performClick()
        composeRule.waitUntilDoesNotExist(hasTestTag(TAG_LOADING), 10000)

        val listHeader = composeRule.onNodeWithTag(TAG_LIST_HEADER, useUnmergedTree = true)
        listHeader.assertIsDisplayed()

        val listItem = composeRule.onNodeWithTag(TAG_LIST_ITEM, useUnmergedTree = true)
        listItem.assertIsDisplayed()

        val itemMovieImage = composeRule.onAllNodesWithTag(TAG_ITEM_IMAGE, useUnmergedTree = true)
        itemMovieImage[0].assertIsDisplayed()

        val itemMovieTitle = composeRule.onAllNodesWithTag(TAG_ITEM_TITLE, useUnmergedTree = true)
        itemMovieTitle[0].assertIsDisplayed()

        val itemMovieDate =
            composeRule.onAllNodesWithTag(TAG_ITEM_RELEASE_DATE, useUnmergedTree = true)
        itemMovieDate[0].assertIsDisplayed()

        val itemMovieVoteAver =
            composeRule.onAllNodesWithTag(TAG_ITEM_VOTE_AVER, useUnmergedTree = true)
        itemMovieVoteAver[0].assertIsDisplayed()
    }
}