package com.example.technicaltest.ui.movieDetail

import android.content.ActivityNotFoundException
import android.content.Intent
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.technicaltest.R
import com.example.technicaltest.navigation.MovieRoutes
import com.example.technicaltest.numberFormat
import com.example.technicaltest.ui.movieList.LoadingIndicator


@Composable
fun MovieDetailView(navController: NavController, id: Int) {
    val viewModel: MovieDetailViewModel = hiltViewModel()
    val viewState = viewModel.viewState
    LaunchedEffect(true){
        viewModel.onUiEvent(MovieDetailViewUiEvent.OnMovieDetailLoaded(id))
    }
    LoadingIndicator(isInProgress = viewState.requestInProgress)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF))
    ) {
        TopBar(navController)
        Spacer(modifier = Modifier.size(8.dp))
        ResultView(movieDetailModel = viewState.result)
    }
}

@Composable
fun TopBar(navController: NavController){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFE11F27))
            .padding(16.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.arrow),
            contentDescription = "",
            modifier = Modifier
                .size(14.dp, 14.dp)
                .clickable { navController.popBackStack() },
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(80.dp))
        Text(
            text = "Movies Details",
            textAlign = TextAlign.Center,
            style = TextStyle(color = Color(0xFFFFFFFF), fontSize = 24.sp, fontWeight = FontWeight.W600)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ResultView(movieDetailModel: MovieDetailModel?){
    val pagerState = rememberPagerState(initialPage = 0, pageCount = {movieDetailModel?.productionCompanies?.size ?: 0}, initialPageOffsetFraction = 0f)
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(8.dp)
        )
        {
            Row(verticalAlignment = Alignment.Top) {
                val context = LocalContext.current
                AsyncImage(
                    modifier = Modifier
                        .size(128.dp, 128.dp),
                    model = "https://image.tmdb.org/t/p/w500" + movieDetailModel?.posterPath,
                    contentDescription = "Open home page",
                )
                Column(
                    Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        modifier = Modifier.clickable {
                            val intent =
                                Intent(Intent.ACTION_VIEW, movieDetailModel?.homePage?.toUri())
                            try {
                                context.startActivity(intent)
                            } catch (e: ActivityNotFoundException) {
                                Log.d("ERROR", "Can not open home page")
                            }
                        },
                        text = "Movie Tile: " + movieDetailModel?.originTitle,
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = Color(0xFF0E0D0D),
                            fontWeight = FontWeight.W600,
                            textDecoration = TextDecoration.Underline,
                        )
                    )
                    Spacer(modifier = Modifier.size(6.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Release Date: " + movieDetailModel?.releaseDate + " | ",
                            style = TextStyle(fontSize = 12.sp, color = Color(0xFF0E0D0D), fontWeight = FontWeight.W400)
                        )
                        if(movieDetailModel?.genres != null){
                            Text(
                                text = (movieDetailModel.genres[0].name
                                    ?: "") + ", " + (movieDetailModel.genres[1].name ?: ""),
                                style = TextStyle(fontSize = 12.sp, color = Color(0xFF0E0D0D))
                            )
                        }
                    }
                    Spacer(modifier = Modifier.size(4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Vote Average: " + movieDetailModel?.voteAverage.toString() + " | ",
                            style = TextStyle(fontSize = 12.sp, color = Color(0xFF0E0D0D),fontWeight = FontWeight.W400)
                        )
                        Text(
                            text = "Status: " + movieDetailModel?.status.toString() + " | ",
                            style = TextStyle(fontSize = 12.sp, color = Color(0xFF0E0D0D), fontWeight = FontWeight.W400)
                        )
                        Text(
                            text = movieDetailModel?.runTime.toString() + " min",
                            style = TextStyle(fontSize = 12.sp, color = Color(0xFF0E0D0D), fontWeight = FontWeight.W400)
                        )
                    }
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = "Budget: " + numberFormat(movieDetailModel?.budget ?: 0) + " $",
                        style = TextStyle(fontSize = 12.sp, color = Color(0xFF0E0D0D), fontWeight = FontWeight.W400)
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Vote: " + movieDetailModel?.voteCount.toString() + " | ",
                            style = TextStyle(fontSize = 12.sp, color = Color(0xFF0E0D0D),fontWeight = FontWeight.W400)
                        )
                        Text(
                            text = "Popularity: " + movieDetailModel?.popularity.toString(),
                            style = TextStyle(fontSize = 12.sp, color = Color(0xFF0E0D0D), fontWeight = FontWeight.W400)
                        )
                    }
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = movieDetailModel?.tagLine.toString(),
                        style = TextStyle(fontSize = 12.sp, color = Color(0xFF0E0D0D))
                    )

                }
            }
        }
        Column( modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()) {
            Text(
                text = "Overview",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color(0xFF000000),
                    fontWeight = FontWeight.W600,
                )
            )
            Text(
                text = movieDetailModel?.overview.toString(),
                style = TextStyle(fontSize = 12.sp, color = Color(0xFF0E0D0D))
            )
        }
        Column( modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
        ) {
            if(movieDetailModel?.productCountries != null){
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Production of Country: " + movieDetailModel.productCountries[0].name,
                    style = TextStyle(fontSize = 14.sp, color = Color(0xFF0E0D0D), fontWeight = FontWeight.W500)
                )
            }

            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(),
                state = pagerState,
                userScrollEnabled = true,
                ) {
                Row (modifier = Modifier.fillMaxWidth()){
                    movieDetailModel?.productionCompanies?.forEach{ item ->
                        CardProduction(productionCompanyModel = item)
                    }
                }
            }
        }
    }
}

@Composable
fun CardProduction(productionCompanyModel: ProductionCompanyModel?){
    Card(
        modifier = Modifier
            .width(128.dp)
            .height(184.dp)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        border = BorderStroke(1.dp, Color.LightGray)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ){
            AsyncImage(
                modifier = Modifier.size(80.dp, 80.dp),
                model = "https://image.tmdb.org/t/p/w500" + productionCompanyModel?.logoPath,
                contentDescription = ""
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = productionCompanyModel?.name ?: "",
                style = TextStyle(fontSize = 12.sp, color = Color(0xFF0E0D0D), fontWeight = FontWeight.W600)
            )
            Text(
                text = "Country: " + productionCompanyModel?.originCountry,
                style = TextStyle(fontSize = 12.sp, color = Color(0xFF0E0D0D), fontWeight = FontWeight.W400)
            )
        }
    }
}
