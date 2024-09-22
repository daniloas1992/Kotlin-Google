package com.danilo.alves.mymovies

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import com.danilo.alves.mymovies.data.MovieRepository.movies
import com.danilo.alves.mymovies.model.MovieScreen
import com.danilo.alves.mymovies.ui.theme.MyMoviesTheme
import com.danilo.alves.mymovies.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyMoviesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MoviesApp()
                }
            }
        }
    }
}

@Composable
fun MoviesApp(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Scaffold (
        topBar = { MoviesTopAppBar()
        },
        modifier = modifier
    ) { it ->
        LazyColumn (contentPadding = it, modifier = modifier) {
            val moviesList = movies.sortedBy { m -> context.resources.getString(m.year) }
            items(moviesList) {
                    MovieScreen(movie = it,
                        modifier = Modifier.padding(10.dp))
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "Movies", style = MaterialTheme.typography.headlineLarge)
        },
        modifier = modifier
    )
}

@Composable
@Preview("Light", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview("Dark", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
fun MoviePreview() {
    MoviesApp()
}