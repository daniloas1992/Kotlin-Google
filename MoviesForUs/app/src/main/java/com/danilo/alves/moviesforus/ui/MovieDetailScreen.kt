package com.danilo.alves.moviesforus.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danilo.alves.moviesforus.R
import com.danilo.alves.moviesforus.data.MoviesLocalDataProvider
import com.danilo.alves.moviesforus.model.Movie

@Composable
fun MovieDetailScreen(movie: Movie, modifier: Modifier = Modifier, contentPadding: PaddingValues = PaddingValues(0.dp)) {

    Column (
        modifier = modifier
            .padding(contentPadding)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card {

            Image(painter = painterResource(id = movie.imageResourceId),
                contentDescription = stringResource(id = movie.title),
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(
                    dimensionResource(id = R.dimen.padding_large)
                )
        )
        {
            Text(text = stringResource(id = movie.title),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_small))
            )

            Row (modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween)
            {
                Text(text = stringResource(id = movie.year), style = MaterialTheme.typography.bodyMedium)
                Text(text = stringResource(id = movie.duration), style = MaterialTheme.typography.bodyMedium)
            }

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))

            Text(text = stringResource(id = movie.sinopse),
                style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))

        }

        Column (modifier = modifier.fillMaxWidth()){

            Text(text = stringResource(id = R.string.trailer),
                style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))

            YoutubePlayer(
                youtubeVideoId = stringResource(id = movie.youtubeVideoId),
                lifecycleOwner = LocalLifecycleOwner.current,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
        }


    }

}

@Preview
@Composable
fun MovieDetailScreenPreview() {
    MovieDetailScreen(MoviesLocalDataProvider.getAllMoviesData().get(0))
}