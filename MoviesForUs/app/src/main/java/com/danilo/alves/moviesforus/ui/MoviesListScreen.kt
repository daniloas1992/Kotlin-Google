package com.danilo.alves.moviesforus.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danilo.alves.moviesforus.R
import com.danilo.alves.moviesforus.data.MoviesLocalDataProvider.getAllMoviesData
import com.danilo.alves.moviesforus.model.GenreMovies
import com.danilo.alves.moviesforus.model.Movie

@Composable
fun MoviesListScreen(genre: GenreMovies, onItemClick: (Movie) -> Unit, contentPadding: PaddingValues = PaddingValues(0.dp), modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val movies = getAllMoviesData().filter{movie -> stringResource(id = movie.genre).contains(genre.genre, true) }.sortedBy { movie -> context.resources.getString(movie.year) }

    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier.padding(top = dimensionResource(R.dimen.padding_large)),
    ) {
        items(movies) { movie ->
            MoviesListItem(
                movie = movie,
                onItemClick = onItemClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesListItem (
    movie: Movie,
    onItemClick: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    Card (
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = { onItemClick(movie) }
    ) {
        Row (verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(R.dimen.card_image_height_large))

        ) {

            MoviesImagemItem(movie, modifier = Modifier.fillMaxHeight())

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = dimensionResource(R.dimen.padding_small),
                        horizontal = dimensionResource(R.dimen.padding_medium)
                    )
                    .weight(1f)
            ) {

                Text(
                    text = stringResource(id = movie.title),
                    style = MaterialTheme.typography.titleMedium,
                )

                Text(
                    text = stringResource(movie.sinopse),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3
                )

                Spacer(Modifier.weight(1f))

                Row (modifier = Modifier){

                    Text(text = stringResource(id = movie.year),
                        style = MaterialTheme.typography.bodySmall)

                    Spacer(Modifier.weight(1f))

                    Text(text = stringResource(id = movie.duration),
                        style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

@Composable
fun MoviesImagemItem(movie: Movie, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(painter = painterResource(id = movie.imageResourceId),
            contentDescription = null,
            alignment = Alignment.BottomStart,
            modifier = modifier.widthIn(max = 150.dp),
            contentScale = ContentScale.FillHeight
        )
    }
}

@Preview
@Composable
fun MoviesListItemPreview() {
    MoviesListItem(getAllMoviesData().get(0), {})
}