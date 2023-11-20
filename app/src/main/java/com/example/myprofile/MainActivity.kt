package com.example.myprofile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myprofile.ui.acteurs.Acteur
import com.example.myprofile.ui.acteurs.ActeurViewModel
import com.example.myprofile.ui.films.Home
import com.example.myprofile.ui.films.HomeViewModel
import com.example.myprofile.ui.films.MovieSelected
import com.example.myprofile.ui.profil.Profil
import com.example.myprofile.ui.series.Serie
import com.example.myprofile.ui.series.SerieSelected
import com.example.myprofile.ui.series.SerieViewModel
import com.example.myprofile.ui.theme.MyProfileTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            val model : HomeViewModel = viewModel()
            val model2 : SerieViewModel = viewModel()
            val model3 : ActeurViewModel = viewModel()

            MyProfileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp), color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "screen"
                    ) {
                        composable("screen") {
                            Profil(navController = navController, windowClass = windowSizeClass)
                        }
                        composable("home") { navBackStackEntry ->
                            //val id = navBackStackEntry.arguments?.getString("id")
                            //on vérifie si l'ID n'est pas null
                            /*id?.let { id ->
                                MovieSelected(navController = navController, viewModel = viewModel(), id = id)
                            }*/
                            Home(navController = navController, model = model, windowClass = windowSizeClass)
                        }
                        composable("serie") {
                            Serie(navController = navController, model = model2, windowClass = windowSizeClass)
                        }
                        composable("acteur") {
                            Acteur(navController = navController, model = model3, windowClass = windowSizeClass)
                        }
                        composable("movieselected/{id}") { navBackStackEntry ->
                            val id = navBackStackEntry.arguments?.getString("id")
                            //on vérifie si l'ID n'est pas null
                            id?.let { id ->
                                MovieSelected(
                                    navController = navController,
                                    model = viewModel(),
                                    id = id,
                                    windowClass = windowSizeClass
                                )
                            }
                        }
                        composable("serieselected/{id}") { navBackStackEntry ->
                            val id = navBackStackEntry.arguments?.getString("id")
                            //on vérifie si l'ID n'est pas null
                            id?.let { id ->
                                SerieSelected(
                                    navController = navController,
                                    model = viewModel(),
                                    id = id,
                                    windowClass = windowSizeClass
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
