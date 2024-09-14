package com.danilo.alves.artgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceMain()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceMain(modifier: Modifier = Modifier) {
    ArtSpaceColumnMain(modifier = modifier.fillMaxSize())
}

@Composable
fun ArtSpaceColumnMain(modifier: Modifier) {

    var position by remember { mutableStateOf(1) }

    val imageResource = getImageResource(position)
    val contentDescription = stringResource(getImageDescription(position))
    val imageTitle = stringResource(getImageTitle(position))

    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween) {

        Surface (
            color = Color.Transparent,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0xffcccccc),
                            Color(0xfff5f5f5)
                        ),
                        tileMode = TileMode.Mirror
                    )
                ),
            tonalElevation = 20.dp) {

            Image(painter = painterResource(id = imageResource),
                contentDescription = contentDescription,
                contentScale = ContentScale.Inside,
                modifier = Modifier.padding(40.dp))
        }

        Spacer(modifier = Modifier.height(10.dp))

        DescriptionText(modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 100.dp),
            imageTitle,
            contentDescription)

        Spacer(modifier = Modifier.height(10.dp))

        Row (modifier = modifier.padding(20.dp, 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom) {
            ElevatedButton(onClick = {
                if(position == 1)
                    position = 9
                else {
                    position--
                }
            }) {
                Text("Anterior")
            }

            ElevatedButton(onClick = {
                if(position == 9) {
                    position = 1
                } else {
                    position++
                }
            }) {
                Text("Próxima")
            }
        }

    }
}

@Composable
fun DescriptionText(modifier: Modifier, title: String, description: String) {
    Column (modifier = modifier
        .padding(20.dp, 10.dp)
        .background(Color(0xFFECEBF4))) {
        Text(text = title, fontSize = 24.sp, modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 5.dp))
        Text(text = description, fontSize = 16.sp, modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp))
    }
}

fun getImageResource(position: Int) : Int {
    return when (position) {
        1 -> R.drawable.image1
        2 -> R.drawable.image2
        3 -> R.drawable.image3
        4 -> R.drawable.image4
        5 -> R.drawable.image5
        6 -> R.drawable.image6
        7 -> R.drawable.image7
        8 -> R.drawable.image8
        else -> R.drawable.image9
    }
}

fun getImageDescription(position: Int) : Int {
    return when (position) {
        1 -> R.string.image1_description
        2 -> R.string.image2_description
        3 -> R.string.image3_description
        4 -> R.string.image4_description
        5 -> R.string.image5_description
        6 -> R.string.image6_description
        7 -> R.string.image7_description
        8 -> R.string.image8_description
        else -> R.string.image9_description
    }
}

fun getImageTitle(position: Int) : Int {
    return when (position) {
        1 -> R.string.image1_title
        2 -> R.string.image2_title
        3 -> R.string.image3_title
        4 -> R.string.image4_title
        5 -> R.string.image5_title
        6 -> R.string.image6_title
        7 -> R.string.image7_title
        8 -> R.string.image8_title
        else -> R.string.image9_title
    }
}