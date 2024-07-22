package com.example.bussinesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bussinesscard.ui.theme.BussinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BussinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainCard()
                }
            }
        }
    }
}

@Composable
fun MainCard(modifier: Modifier = Modifier) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.background(color = Color(0XFFD2E8D4))
    ) {
        ImageCard(modifier.align(alignment = Alignment.CenterHorizontally).padding(top = 120.dp))
        MainText(modifier.align(alignment = Alignment.CenterHorizontally))
        MainContact(modifier/*.align(alignment = Alignment.Start)*/)
    }
}

@Composable
fun ImageCard (modifier: Modifier = Modifier) {
    val image = painterResource(id = R.drawable.android_logo)
    Image(
        painter = image,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier.size(80.dp).background(color = Color(0XFF073042))
    )
}

@Composable
fun MainText (modifier: Modifier = Modifier) {
    Text(
        text = "Danilo Alves dos Santos",
        fontSize = 22.sp,
        color = Color.Black,
        modifier = modifier.padding(start = 15.dp, end = 15.dp)
    )
    Text(
        text = "Android Developer",
        fontSize = 16.sp,
        color = Color(0,107,54),
        modifier = modifier.padding(bottom = 36.dp, top = 10.dp)
    )
}

@Composable
fun MainContact(modifier: Modifier = Modifier) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(top = 20.dp)
    ) {
        val imagePhone = painterResource(id = R.drawable.icon_phone)
        Contact(imagePhone, "+55 (14) 98166-6513        ", modifier)
        val imageShare = painterResource(id = R.drawable.icon_share)
        Contact(imageShare,"@DaniloAlves                     ",modifier)
        val imageMail = painterResource(id = R.drawable.icon_mail)
        Contact(imageMail, "daniloas1992@gmail.com",modifier)
    }
}

@Composable
fun Contact(image: Painter, contact: String, modifier: Modifier = Modifier) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier.size(25.dp)
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = contact,
            color = Color.Black,
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainCardPreview() {
    BussinessCardTheme {
        MainCard()
    }
}