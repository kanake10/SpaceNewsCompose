package com.example.spacenews.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.Image
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.spacenews.SpaceNewsViewModel
import com.example.spacenews.domain.ArticleDomain
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun SpaceNewsScreen(
    navController: NavController,
    viewModel: SpaceNewsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.articles) { data ->
                SpaceNewsItem(
                    articleDomain = data,
                    onItemClick = {
                        navController.navigate(Screen.ArticleDetailScreen.route + "/${data.id}")
                    }
                )
            }
        }
        if(state.error.isNotBlank()) {
            Text(
                text = state.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

sealed class Screen(val route: String) {
    object ArticleScreen: Screen("article_list")
    object ArticleDetailScreen: Screen("details")
}

@Composable
fun SpaceNewsItem(articleDomain: ArticleDomain,
                  onItemClick: (ArticleDomain) -> Unit
                  ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(15.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onItemClick(articleDomain) }
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .height(190.dp),
                    painter = rememberImagePainter(data = articleDomain.imageUrl),
                    contentDescription = " image",
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = articleDomain.newsSite,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(6.dp),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.Black,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = articleDomain.summary,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(6.dp),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.Black,
                    maxLines = 1
                )
            }
        }
}
