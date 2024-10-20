package com.danilo.alves.whereismybook.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.danilo.alves.whereismybook.R
import com.danilo.alves.whereismybook.model.Book
import com.danilo.alves.whereismybook.ui.BookUiState
import com.danilo.alves.whereismybook.ui.BooksViewModel

@Composable
fun BookDetailsScreen(booksViewModel: BooksViewModel, retryAction: () -> Unit, modifier: Modifier = Modifier, contentPadding: PaddingValues = PaddingValues(0.dp)) {

    Column (modifier = Modifier.padding(contentPadding)) {

        val bookUiState = booksViewModel.bookUiState

        when(bookUiState) {
            is BookUiState.Success -> BookDetailsCard(bookUiState.book)
            is BookUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
            is BookUiState.Error -> ErrorScreen(retryAction = retryAction, modifier.fillMaxSize())

        }
    }
}

@Composable
fun BookDetailsCard(book: Book, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier = modifier) {
            val url = (book.volumeInfo.imageLinks.thumbnail)?.replace("http", "https")
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current).data(url)
                    .crossfade(true).build(),
                contentDescription = "Book",
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}