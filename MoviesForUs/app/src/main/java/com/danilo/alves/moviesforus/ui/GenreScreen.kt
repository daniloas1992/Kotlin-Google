package com.danilo.alves.moviesforus.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danilo.alves.moviesforus.R
import com.danilo.alves.moviesforus.model.GenreMovies
import com.danilo.alves.moviesforus.model.MoviesForUsUiState

@Composable
fun GenreScreen(modifier: Modifier = Modifier, onItemClick: (GenreMovies) -> Unit, contentPadding: PaddingValues = PaddingValues(0.dp)) {

    val genres = GenreMovies.entries

    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier.padding(top = dimensionResource(R.dimen.padding_large)),
    ) {
        items(genres) { genre ->
            GenreListItem(
                genre = genre,
                onItemClick = onItemClick,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreListItem (
    genre: GenreMovies,
    onItemClick: (GenreMovies) -> Unit,
    modifier: Modifier = Modifier
) {
    Card (
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = { onItemClick(genre) }
    ) {
        Row (verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(R.dimen.card_image_height_small))

        ) {
            GenreImagemItem(genre, modifier = Modifier)

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = modifier.fillMaxWidth()) {

                Text(
                    text = genre.genre,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun GenreImagemItem(genre: GenreMovies, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(painter = painterResource(id = genre.genreImageResourceId),
            contentDescription = null,
            alignment = Alignment.BottomStart,
            modifier = Modifier.widthIn(max = 100.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
fun GenreListItemPreview() {
    GenreListItem(GenreMovies.Acao, {})
}