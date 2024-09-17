package com.danilo.alves.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danilo.alves.superheroes.data.HeroesRepository.heroes
import com.danilo.alves.superheroes.model.HeroesScreen
import com.danilo.alves.superheroes.ui.theme.SuperHeroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperHeroesTheme {
                Surface (modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    HeroesApp()
                }
            }
        }
    }
}

@Composable
fun HeroesApp(modifier: Modifier = Modifier) {

    Scaffold (
        topBar = {
            HeroesTopAppBar()
        }
    ) { it ->
        LazyColumn (contentPadding = it) {
            items(heroes) {
                HeroesScreen(hero = it,
                    modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroesTopAppBar(modifier: Modifier = Modifier) {

    CenterAlignedTopAppBar(
        title = {
            Row {
                Text(text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.displayLarge)
            }
        },
        modifier = modifier
    )

}


@Preview(showBackground = true)
@Composable
fun HeroesPreview() {
    SuperHeroesTheme(darkTheme = false) {
        HeroesApp()
    }
}

@Preview(showBackground = true)
@Composable
fun HeroesDarkThemePreview() {
    SuperHeroesTheme (darkTheme = true) {
        HeroesApp()
    }
}