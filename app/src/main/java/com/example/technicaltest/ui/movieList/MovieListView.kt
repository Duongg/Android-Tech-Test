package com.example.technicaltest.ui.movieList

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.technicaltest.IMAGE_URL
import com.example.technicaltest.R
import com.example.technicaltest.TAG_ITEM_IMAGE
import com.example.technicaltest.TAG_ITEM_MOVIE
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
import com.example.technicaltest.ui.ErrorDialog
import com.example.technicaltest.ui.theme.TechnicalTestTheme


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MovieListView(navController: NavController, viewModel: MoviesListViewModel = hiltViewModel()) {
    val viewState = viewModel.viewState
    LoadingIndicator(isInProgress = viewState.requestInProgress)
    ErrorDialog(
        viewState.errorMessage,
        onOKClicked = remember {
            { viewModel.onUiEvent(MoviesListViewUiEvent.OnOKClicked()) }
        })
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF))
            .semantics { testTagsAsResourceId = true }
    ) {
        TopBar()
        SearchBox(
            viewState = viewState,
            onSearchChanged = remember {
                {
                    viewModel.onUiEvent(MoviesListViewUiEvent.SearchChanged(it))
                }
            },
            onSearchButtonClicked = remember {
                {
                    viewModel.onUiEvent(MoviesListViewUiEvent.SearchClick(it))
                }
            }
        )
        Column(modifier = Modifier.fillMaxSize().semantics { testTagsAsResourceId = true }) {
            Row(
                modifier = Modifier
                    .padding(16.dp, 0.dp, 0.dp, 0.dp)
                    .fillMaxWidth()
                    .semantics { testTag = TAG_LIST_HEADER },
                horizontalArrangement = Arrangement.Start,
            ) {
                Text(
                    text = if (viewState.isTrendingMovie) stringResource(id = R.string.movie_trending_header) else stringResource(
                        id = R.string.movie_search_result_header
                    ),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = Color(0xFFE11F27),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W500
                    )
                )
            }
            when {
                !viewState.results.isNullOrEmpty() -> ResultView(
                    listItem = viewState.results,
                    onItemScrolled = remember {
                        {
                            viewModel.onUiEvent(MoviesListViewUiEvent.ItemScrolled(it))
                        }
                    },
                    navController,
                )

                viewState.results?.isEmpty() == true -> NoResultView()
                else -> EmptyView()
            }
        }
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFE11F27))
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.semantics { testTag = TAG_MOVIE_TITLE_LIST},
            text = stringResource(id = R.string.movie_title_list),
            textAlign = TextAlign.Center,
            style = TextStyle(
                color = Color(0xFFFFFFFF),
                fontSize = 24.sp,
                fontWeight = FontWeight.W600
            )
        )
    }
}

@Suppress("UNUSED_EXPRESSION")
@Composable
fun SearchBox(
    viewState: MoviesListViewState,
    onSearchChanged: ((String) -> Unit)? = null,
    onSearchButtonClicked: ((String) -> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .semantics { testTag = TAG_SEARCH_FIELD },
            textStyle = TextStyle(fontSize = 16.sp),
            value = viewState.searchInput.orEmpty(),
            onValueChange = { onSearchChanged?.invoke(it) },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.movie_placeholder),
                    style = TextStyle(Color(0xFF5A5A5A))
                )
            }
        )
        Spacer(modifier = Modifier.size(16.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .weight(1f)
                .semantics { testTag = TAG_SEARCH_BUTTON },
            onClick = {
                onSearchButtonClicked?.invoke(viewState.searchInput.orEmpty())
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE11F27))
        )
        {
            Text(
                text = stringResource(id = R.string.movie_search_btn),
                style = TextStyle(Color(0xFFFFFFFF))
            )
        }
    }
}

@Composable
fun ResultView(
    listItem: List<MovieItemModel>? = null,
    onItemScrolled: ((Int) -> Unit)? = null,
    navController: NavController,
) {
    val listState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .background(Color.White)
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(16.dp)
            .semantics { testTag = TAG_LIST_ITEM },
        state = listState,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(requireNotNull(listItem)) { index, item ->
            LaunchedEffect(key1 = true) {
                onItemScrolled?.invoke(index)
            }
            MovieItemCard(
                itemModel = item,
                navController = navController
            )
        }
    }
}

@Composable
fun NoResultView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.no_result),
            textAlign = TextAlign.Center,
            style = TextStyle(
                color = Color(0xFFE11F27),
                fontSize = 18.sp,
                fontWeight = FontWeight.W500
            )
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoadingIndicator(isInProgress: Boolean) {
    if (!isInProgress) {
        return
    }
    Dialog(
        onDismissRequest = {},
        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Box(modifier = Modifier.fillMaxSize().semantics { testTagsAsResourceId = true }, contentAlignment = Alignment.Center) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Transparent
            ) {
                Box(modifier = Modifier.fillMaxSize().semantics { testTag = TAG_LOADING }, contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(40.dp),
                        strokeWidth = 4.dp
                    )
                }
            }
        }
    }


}

@Composable
fun EmptyView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MovieItemCard(
    itemModel: MovieItemModel,
    navController: NavController,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth().semantics { testTagsAsResourceId = true },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .clickable {
                    navController.navigate(MovieRoutes.MovieDetailScreen.route + "?id=${itemModel.id}")
                }
                .padding(16.dp)
                .semantics { testTag = TAG_ITEM_MOVIE }
        )
        {
            Row {
                AsyncImage(
                    modifier = Modifier.size(72.dp, 72.dp).semantics { testTag = TAG_ITEM_IMAGE },
                    model = IMAGE_URL + itemModel.image,
                    contentDescription = ""
                )
                Column(
                    Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.semantics { testTag = TAG_ITEM_TITLE },
                        text = stringResource(id = R.string.movie_title_item) + itemModel.movieTitle,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color(0xFF0E0D0D),
                            fontWeight = FontWeight.W600
                        )
                    )
                    Spacer(modifier = Modifier.size(6.dp))
                    Text(
                        modifier = Modifier.semantics { testTag = TAG_ITEM_RELEASE_DATE },
                        text = stringResource(id = R.string.movie_release_date) + itemModel.year,
                        style = TextStyle(
                            fontSize = 10.sp,
                            color = Color(0xFF0E0D0D),
                            fontWeight = FontWeight.W400
                        )
                    )
                    Spacer(modifier = Modifier.size(6.dp))
                    Text(
                        modifier = Modifier.semantics { testTag = TAG_ITEM_VOTE_AVER },
                        text = stringResource(id = R.string.movie_vote_average) + itemModel.voteAverage.toString(),
                        style = TextStyle(
                            fontSize = 10.sp,
                            color = Color(0xFF0E0D0D),
                            fontWeight = FontWeight.W400
                        )
                    )
                }
            }


        }
    }
}

@Preview
@Composable
fun MovieListPreview() {
    Surface {
        TechnicalTestTheme {
            MovieListView(navController = rememberNavController())
        }
    }
}