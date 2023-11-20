package com.example.myprofile.ui.series

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun SerieSelected(navController: NavController, model: SerieSelectedViewModel, id: String, windowClass: WindowSizeClass) {
    val serieSelected = model.serie.collectAsState()
    val classeLargeur = windowClass.widthSizeClass

    LaunchedEffect(true) {
        model.getSerieSelected(id)
    }

    when(classeLargeur) {
        WindowWidthSizeClass.Compact -> {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = serieSelected.value.original_name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Default
                )

                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500/" + serieSelected.value.backdrop_path,
                    contentDescription = null,
                )

                Text(
                    text = serieSelected.value.last_air_date,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Default
                )

                Text(
                    text = "Langue: " + serieSelected.value.original_language,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Default
                )

                Text(
                    text = "Popularité: " + serieSelected.value.popularity,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Default
                )

                Text(
                    text = "Vote: " + serieSelected.value.vote_count,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Default
                )
                Spacer(modifier = Modifier.padding(10.dp))

                Text(
                    text = "Résumé: " + serieSelected.value.overview,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Default,
                    textAlign = TextAlign.Justify
                )


            }
        } // ajout

        else -> {
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.SpaceEvenly) {
                    Text(
                        text = serieSelected.value.original_name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        fontFamily = FontFamily.Default
                    )

                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500/" + serieSelected.value.backdrop_path,
                        contentDescription = null,
                    )

                    Text(
                        text = serieSelected.value.last_air_date,
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Default
                    )

                    Text(
                        text = "Langue: " + serieSelected.value.original_language,
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Default
                    )

                    Text(
                        text = "Popularité: " + serieSelected.value.popularity,
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Default
                    )

                    Text(
                        text = "Vote: " + serieSelected.value.vote_count,
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Default
                    )
                }

                Spacer(modifier = Modifier.padding(26.dp))

                Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceEvenly) {
                    Text(
                        text = "Résumé: " + serieSelected.value.overview,
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Default,
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }

    } //ajout

}