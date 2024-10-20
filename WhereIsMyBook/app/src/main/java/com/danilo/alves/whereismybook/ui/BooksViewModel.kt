package com.danilo.alves.whereismybook.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.danilo.alves.whereismybook.WhereIsMyBookApplication
import com.danilo.alves.whereismybook.data.BooksRepository
import com.danilo.alves.whereismybook.model.Book
import com.danilo.alves.whereismybook.model.Bookcase
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface BookListUiState {
    data class Success(val bookcase: Bookcase): BookListUiState
    object Error: BookListUiState
    object Loading: BookListUiState
}

sealed interface BookUiState {
    data class Success(val book: Book): BookUiState
    object Error: BookUiState
    object Loading: BookUiState
}


class BooksViewModel(private var booksRepository: BooksRepository) : ViewModel() {

    var bookListUiState: BookListUiState by mutableStateOf(BookListUiState.Loading)
        private set

    var bookUiState: BookUiState by mutableStateOf(BookUiState.Loading)
        private set

    var booksSearch by mutableStateOf(defaultBooksSearch)
        private set

    var isShowingBookcasePage by mutableStateOf(true)
        private set


    init {
        getBookList()
    }

    fun updateSearchBooks(booksSearch: String) {
        this.booksSearch = booksSearch
    }

    fun updateShowingBookcasePage() {
        isShowingBookcasePage = !isShowingBookcasePage
    }

    fun getBookList() {

        val searchQueryFormated = booksSearch.replace(" ", "+")

        viewModelScope.launch {
            bookListUiState = try {
                BookListUiState.Success(booksRepository.getBookcase(searchQueryFormated))
            } catch (e: IOException) {
                BookListUiState.Error
            } catch (e: HttpException) {
                BookListUiState.Error
            }
        }
    }

    fun updateBook(book: Book) {

        viewModelScope.launch {
            bookUiState = try {
                BookUiState.Success(booksRepository.getBook(book.id))
            } catch (e: IOException) {
                BookUiState.Error
            } catch (e: HttpException) {
                BookUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as WhereIsMyBookApplication)
                val booksRepository = application.container.booksRepository
                BooksViewModel(booksRepository=booksRepository)
            }
        }

        const val defaultBooksSearch: String = "Lançamentos"

    }
}