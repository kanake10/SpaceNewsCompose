package com.example.spacenews.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController

@Composable
fun DetailScreen(
    navController: NavController,
    id: Int,
    viewModel: DetailViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        viewModel.getArticlesDetails(id)
    }

    val state = viewModel.state.value

    Box (modifier = Modifier.fillMaxSize()) {
        state.articlesDetails?.let { details ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp),
            ){
                item {
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .height(190.dp),
                            painter = rememberImagePainter(data = details.imageUrl),
                            contentDescription = " image",
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = details.newsSite,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(6.dp),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.SansSerif,
                            color = Color.Black,
                            maxLines = 1
                        )

                        Text(
                            text = details.title,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(6.dp),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.SansSerif,
                            color = Color.Black,
                            maxLines = 1
                        )

                        Text(
                            text = details.summary,
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
        }

    }

}

