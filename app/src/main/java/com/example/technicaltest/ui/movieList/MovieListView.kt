package com.example.technicaltest.ui.movieList

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import com.example.technicaltest.navigation.MovieRoutes
import com.example.technicaltest.ui.theme.TechnicalTestTheme


@Composable
fun MovieListView(navController: NavController, viewModel: MoviesListViewModel = hiltViewModel()) {
    val viewState = viewModel.viewState
    LoadingIndicator(isInProgress = viewState.requestInProgress)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF))
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
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .padding(16.dp, 0.dp, 0.dp, 0.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
            ) {
                Text(
                    text = if (viewState.isTrendingMovie) "Trending Movies" else "Search Results",
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
                    onItemClicked = remember {
                        {
                            viewModel.onUiEvent(MoviesListViewUiEvent.OnMovieItemClicked(it))
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
fun TopBar(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFE11F27))
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Movies List",
            textAlign = TextAlign.Center,
            style = TextStyle(color = Color(0xFFFFFFFF), fontSize = 24.sp, fontWeight = FontWeight.W600)
        )
    }
}

@Composable
fun SearchBox(
    viewState: MoviesListViewState,
    onSearchChanged: ((String) -> Unit)? = null,
    onSearchButtonClicked: ((String) -> Unit)? = null,
){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
            textStyle = TextStyle(fontSize = 16.sp),
            value = viewState.searchInput.orEmpty(),
            onValueChange = { onSearchChanged?.invoke(it)}
        )
        Spacer(modifier = Modifier.size(16.dp))
        Button(modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .weight(1f),
            onClick = {
                onSearchButtonClicked?.invoke(viewState.searchInput.orEmpty())
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE11F27)))
        {
            Text(text = "Search", style = TextStyle(Color(0xFFFFFFFF)))
        }
    }
}
@Composable
fun ResultView(listItem: List<MovieItemModel>? = null, onItemClicked: ((Int) -> Unit)? = null, navController: NavController,){
    val listState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .background(Color.White)
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(16.dp),
        state = listState,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        itemsIndexed(requireNotNull(listItem)){ index, item ->
            MovieItemCard(itemModel = item, onItemClicked = onItemClicked, navController = navController)
        }
    }
}
@Composable
fun NoResultView(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Opps! No Results",
            textAlign = TextAlign.Center,
            style = TextStyle(
                color = Color(0xFFE11F27),
                fontSize = 18.sp,
                fontWeight = FontWeight.W500
            )
        )
    }
}
@Composable
fun LoadingIndicator(isInProgress: Boolean){
    if (!isInProgress) {
        return
    }
    Dialog(
        onDismissRequest = {},
        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Transparent
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
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
fun EmptyView(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

    }
}
@Composable
fun MovieItemCard(
    itemModel: MovieItemModel,
    onItemClicked: ((Int) -> Unit)? = null,
    navController: NavController,
){
    Card(
        modifier = Modifier
            .fillMaxWidth(),
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
                    onItemClicked?.invoke(itemModel.id)
                    navController.navigate(MovieRoutes.MovieDetailScreen.route + "?id=${itemModel.id}")
                }
                .padding(16.dp)
        )
        {
            Row {
                AsyncImage(
                    modifier = Modifier.size(72.dp, 72.dp),
                    model = "https://image.tmdb.org/t/p/w500" + itemModel.image,
                    contentDescription = ""
                )
                Column(
                    Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Movie Tile: " + itemModel.movieTitle,
                        style = TextStyle(fontSize = 16.sp, color = Color(0xFF0E0D0D), fontWeight = FontWeight.W600)
                    )
                    Spacer(modifier = Modifier.size(6.dp))
                    Text(
                        text = "Release Date: " + itemModel.year,
                        style = TextStyle(fontSize = 10.sp, color = Color(0xFF0E0D0D),fontWeight = FontWeight.W400)
                    )
                    Spacer(modifier = Modifier.size(6.dp))
                    Text(
                        text = "Vote Average: " + itemModel.voteAverage.toString(),
                        style = TextStyle(fontSize = 10.sp, color = Color(0xFF0E0D0D), fontWeight = FontWeight.W400)
                    )
                }
            }


        }
    }
}

@Preview
@Composable
fun MovieListPreview(){
    Surface {
        TechnicalTestTheme {
            MovieListView(navController = rememberNavController())
        }
    }
}