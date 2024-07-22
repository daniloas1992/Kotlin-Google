package com.danilo.alves.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danilo.alves.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lemonade()
        }
    }
}

@Preview()
@Composable
fun Lemonade(modifier: Modifier = Modifier) {
    LemonadeTree(
        modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}


@Composable
fun LemonadeTree(modifier: Modifier) {

    var result by remember { mutableStateOf(1) }

    val imageResource = when (result) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val description = when (result) {
        1 -> R.string.lemon_tree_content_description
        2 -> R.string.lemon_content_description
        3 -> R.string.glass_of_lemonad_content_descripti
        else -> R.string.empty_glass_content_description
    }

    val instruction = when (result) {
        1 -> R.string.tap_lemon_tree
        2 -> R.string.keep_tapping
        3 -> R.string.tap_lemonade
        else -> R.string.tap_empty_glass
    }

    Column (modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally)
    {
        Image(painter = painterResource(id = imageResource), contentDescription = description.toString())
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = stringResource(instruction), fontSize =  18.sp)
    }
}