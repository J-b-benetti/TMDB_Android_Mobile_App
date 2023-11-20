package com.example.myprofile.ui.profil

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myprofile.R

@Composable
fun Profil(navController: NavController, windowClass: WindowSizeClass) {
    val classeLargeur = windowClass.widthSizeClass
    when (classeLargeur) {
        WindowWidthSizeClass.Compact -> {
            //Log.e("xxx", "on est compact")
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Bonjour()
                ImageProfil()
                Profil()
                Coordonnes()
                DemarrerButton(navController)
            }
        }

        else -> {
            //Log.e("xxx", "on est pas en compact")
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.SpaceEvenly) {
                    Bonjour()
                    ImageProfil()
                    Profil()
                }
                Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceEvenly) {
                    Coordonnes()
                    DemarrerButton(navController)
                }
            }
        }
    }
}

@Composable
fun Bonjour() {
    val prenom = "Jean-Baptiste"
    Text(
        text = "Bonjour ${prenom} !",
        fontSize = 40.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.Black,
        fontFamily = FontFamily.Cursive,
        modifier = Modifier.padding(bottom = 15.dp)
    )
}

@Composable
fun ImageProfil() {

    Image(
        painterResource(id = R.drawable.photo_cv),
        contentScale = ContentScale.Crop,
        contentDescription = "Image de profil",
        modifier = Modifier
            .clip(CircleShape)
            .size(200.dp))
}

@Composable
fun Profil() {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = "Jean-Baptiste BENETTI",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontFamily = FontFamily.SansSerif
        )

        Text(
            text = "Alternant en informatique",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            fontFamily = FontFamily.SansSerif
        )

        Text(
            text = "Ecole d'Ingénieurs ISIS",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            fontFamily = FontFamily.SansSerif
        )
    }
}


@Composable
fun Coordonnes() {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(id = R.drawable.baseline_attach_email_24),
                contentDescription = "Image de profil",
                modifier = Modifier
                    .size(23.dp)
            )

            Text(
                text = "jean-baptiste.benetti@univ-jfc.fr",
                fontSize = 19.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color.Black,
                modifier = Modifier.padding(start = 10.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
            //horizontalArrangement = Arrangement.End
        ) {
            Image(
                painterResource(id = R.drawable.linkedin_socialnetwork_17441),
                contentDescription = "Image de profil",
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(20.dp)
            )

            Text(
                text = "www.linkedin.com/in/jean-baptiste-benetti",
                fontSize = 18.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color.Black,
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }
}

@Composable
fun DemarrerButton(navController: NavController) {

    Button(onClick = {
        navController.navigate("home")

    }, colors = ButtonDefaults.buttonColors(Color.DarkGray)) {
        Text(
            text = "Démarrrer",
            color = Color.Cyan,
            fontFamily = FontFamily.Monospace,
            fontSize = 19.sp
        )
    }
}