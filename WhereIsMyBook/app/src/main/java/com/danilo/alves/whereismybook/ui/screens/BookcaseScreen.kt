package com.danilo.alves.whereismybook.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.danilo.alves.whereismybook.R
import com.danilo.alves.whereismybook.model.Book
import com.danilo.alves.whereismybook.ui.BookListUiState
import com.danilo.alves.whereismybook.ui.BooksViewModel

@Composable
fun BookcaseScreen(
    booksViewModel: BooksViewModel,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(contentPadding)
    ) {

        val bookListUiState = booksViewModel.bookListUiState

        SearchBooks(
            booksSearch = booksViewModel.booksSearch,
            onBooksSearchChanged = { booksViewModel.updateSearchBooks(it) },
            onKeyboardDone = { booksViewModel.getBookList() },
            modifier = modifier
        )

        when (bookListUiState) {
            is BookListUiState.Success -> BookListScreen(
                booksList = bookListUiState.bookcase.items,
                onBookClick = {
                    booksViewModel.updateBook(it)
                    booksViewModel.updateShowingBookcasePage()
                },
                contentPadding = contentPadding,
                modifier = modifier
            )

            is BookListUiState.Error -> ErrorScreen(
                retryAction = retryAction,
                modifier.fillMaxSize()
            )

            is BookListUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        }
    }
}

@Composable
fun SearchBooks(
    booksSearch: String,
    onBooksSearchChanged: (String) -> Unit,
    onKeyboardDone: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = booksSearch,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = MaterialTheme.colorScheme.surface,
        ),
        onValueChange = onBooksSearchChanged,
        label = { Text(text = "Search books") },
        isError = false,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { onKeyboardDone() }
        )
    )
}

@Composable
fun BookListScreen(
    booksList: List<Book>,
    onBookClick: (Book) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {

    LazyVerticalGrid(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        columns = GridCells.Fixed(2),
        modifier = modifier
    ) {
        items(items = booksList, key = { book -> book.id }) { book ->
            BookCard(book = book, onBookClick = onBookClick, modifier = modifier)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookCard(book: Book, onBookClick: (Book) -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        onClick = {onBookClick(book)}
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