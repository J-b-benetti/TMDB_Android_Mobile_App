package com.example.myprofile.ui.acteurs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import com.example.myprofile.R
import com.example.myprofile.ui.models.Actor

@Composable
fun Acteur(navController: NavController, model: ActeurViewModel, windowClass: WindowSizeClass) {
    val listeActeur = model.acteurs.collectAsState()

    ScaffoldApp(navController, listeActeur.value, model, windowClass)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarSample(active : MutableState<Boolean>, model: ActeurViewModel) {
    var text by rememberSaveable { mutableStateOf("") }

    Box(
        Modifier
            .fillMaxWidth()
            .padding(3.dp)
            .clip(RoundedCornerShape(10.dp))
            .semantics { isTraversalGroup = true }) {
        SearchBar(
            query = text,
            onQueryChange = { text = it },
            onSearch = { active.value = false
                model.searchActors(text)},
            active = active.value,
            placeholder = { Text("Mot-clé") },
            onActiveChange = { active.value = it },
            modifier = Modifier
                .height(70.dp)
                .align(Alignment.TopCenter)
                .semantics { traversalIndex = -1f },
            windowInsets = SearchBarDefaults.windowInsets
        ) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldApp(navController : NavController, acteurs : List<Actor>, model: ActeurViewModel, windowClass: WindowSizeClass) {
    var active = rememberSaveable { mutableStateOf(false) }
    val classeLargeur = windowClass.widthSizeClass

    when(classeLargeur) {
        WindowWidthSizeClass.Compact -> {
            Scaffold(
                topBar = {
                    if (active.value) {
                        SearchBarSample(active, model)
                    } else {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                                    70.dp
                                ),
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            navigationIcon = {
                                IconButton(onClick = { navController.navigate("serie") }) {
                                    Icon(
                                        modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                                        imageVector = Icons.Filled.ArrowBack,
                                        contentDescription = "Retour arrière"
                                    )
                                }
                            },
                            title = {
                                Text("Movie's App")
                            },
                            actions = {
                                IconButton(onClick = { active.value = true }) {
                                    Icon(
                                        imageVector = Icons.Filled.Search,
                                        contentDescription = null
                                    )
                                }
                            }
                        )
                    }
                },
                bottomBar = {
                    BottomNavigation(backgroundColor = MaterialTheme.colorScheme.primary) {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination

                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.clap),
                                    contentDescription = "film"
                                )
                            },
                            label = { Text(text = "Films") },
                            selected = currentDestination?.hierarchy?.any { true } == true,
                            onClick = { navController.navigate("home") }
                        )

                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.smart),
                                    contentDescription = "série"
                                )
                            },
                            label = { Text(text = "Séries") },
                            selected = currentDestination?.hierarchy?.any { true } == true,
                            onClick = { navController.navigate("serie") }
                        )

                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.acteur),
                                    contentDescription = "acteur"
                                )
                            },
                            label = { Text(text = "Acteurs") },
                            selected = currentDestination?.hierarchy?.any { true } == true,
                            onClick = { navController.navigate("acteur") }
                        )
                    }
                }

            ) { innerPadding ->
                LazyVerticalGrid(
                    modifier = Modifier
                        .padding(innerPadding),
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    verticalArrangement = Arrangement.spacedBy(18.dp)
                ) {

                    items(acteurs) { acteur ->
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White,
                                contentColor = Color.Black
                            )
                        ) {
                            Column(
                                Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                AsyncImage(
                                    modifier = Modifier.clip(shape = RoundedCornerShape(8.dp)),
                                    model = "https://image.tmdb.org/t/p/w500/" + acteur.profile_path,
                                    contentDescription = null,
                                )
                                Text(
                                    text = acteur.name,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.Default
                                )
                                Text(
                                    text = acteur.known_for_department,
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = FontFamily.Default
                                )
                            }
                        }
                    }
                }

            }

        } //ajout

        else -> {
            Scaffold(
                topBar = {
                    if (active.value) {
                        SearchBarSample(active, model)
                    } else {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                                    70.dp
                                ),
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            navigationIcon = {
                                IconButton(onClick = { navController.navigate("serie") }) {
                                    Icon(
                                        modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                                        imageVector = Icons.Filled.ArrowBack,
                                        contentDescription = "Retour arrière"
                                    )
                                }
                            },
                            title = {
                                Text("Movie's App")
                            },
                            actions = {
                                IconButton(onClick = { active.value = true }) {
                                    Icon(
                                        imageVector = Icons.Filled.Search,
                                        contentDescription = null
                                    )
                                }
                            }
                        )
                    }
                },
                bottomBar = {
                    BottomNavigation(backgroundColor = MaterialTheme.colorScheme.primary) {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination

                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.clap),
                                    contentDescription = "film"
                                )
                            },
                            label = { Text(text = "Films") },
                            selected = currentDestination?.hierarchy?.any { true } == true,
                            onClick = { navController.navigate("home") }
                        )

                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.smart),
                                    contentDescription = "série"
                                )
                            },
                            label = { Text(text = "Séries") },
                            selected = currentDestination?.hierarchy?.any { true } == true,
                            onClick = { navController.navigate("serie") }
                        )

                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.acteur),
                                    contentDescription = "acteur"
                                )
                            },
                            label = { Text(text = "Acteurs") },
                            selected = currentDestination?.hierarchy?.any { true } == true,
                            onClick = { navController.navigate("acteur") }
                        )
                    }
                }

            ) { innerPadding ->
                LazyVerticalGrid(
                    modifier = Modifier
                        .padding(innerPadding),
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.spacedBy(3.dp),
                    verticalArrangement = Arrangement.spacedBy(18.dp)
                ) {

                    items(acteurs) { acteur ->
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White,
                                contentColor = Color.Black
                            )
                        ) {
                            Column(
                                Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                AsyncImage(
                                    modifier = Modifier.clip(shape = RoundedCornerShape(8.dp)),
                                    model = "https://image.tmdb.org/t/p/w500/" + acteur.profile_path,
                                    contentDescription = null,
                                )
                                Text(
                                    text = acteur.name,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.Default
                                )
                                Text(
                                    text = acteur.known_for_department,
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = FontFamily.Default
                                )
                            }
                        }
                    }
                }

            }
        }

    } //ajout
}