package com.danilo.alves.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.danilo.alves.amphibians.R
import com.danilo.alves.amphibians.model.Amphibian
import com.danilo.alves.amphibians.ui.AmphibiansUiState

@Composable
fun HomeScreen(
    amphibiansUiState: AmphibiansUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    when(amphibiansUiState) {
        is AmphibiansUiState.Success -> AmphibianListScreen(amphibiansList = amphibiansUiState.amphibians, modifier, contentPadding)
        is AmphibiansUiState.Error -> ErrorScreen(retryAction = retryAction, modifier.fillMaxSize())
        is AmphibiansUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = stringResource(id = R.string.loading)
    )
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = "Connection Error"
        )
        Text(
            text = stringResource(id = R.string.loading_failed),
            modifier = Modifier.padding(16.dp)
        )
        Button(onClick = retryAction) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

@Composable
fun AmphibianListScreen(amphibiansList: List<Amphibian>, modifier: Modifier = Modifier, contentPadding: PaddingValues = PaddingValues(0.dp)) {
    LazyColumn(contentPadding = contentPadding, modifier = modifier) {
        items(items = amphibiansList, key = { amphibian -> amphibian.name}) {
            amphibian -> AmphibianDetailCard(amphibian = amphibian, modifier = modifier
            .fillMaxWidth())
        }
    }
}

@Composable
fun AmphibianDetailCard(amphibian: Amphibian, modifier: Modifier = Modifier) {

    Card (modifier = modifier.padding(10.dp),
          elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {

        Column (modifier = modifier ) {

            Text(text = amphibian.name, style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(10.dp))

            Spacer(modifier = modifier.height(5.dp))

            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current).data(amphibian.imgSrc).crossfade(true).build(),
                contentDescription = stringResource(id = R.string.amphibian_photo),
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = modifier.height(5.dp))

            Text(text = amphibian.description, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(10.dp))

        }
    }
}