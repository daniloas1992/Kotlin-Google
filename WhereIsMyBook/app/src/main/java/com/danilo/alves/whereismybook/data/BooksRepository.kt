package com.danilo.alves.whereismybook.data

import com.danilo.alves.whereismybook.model.Book
import com.danilo.alves.whereismybook.model.Bookcase
import com.danilo.alves.whereismybook.network.BooksApiService

interface BooksRepository {
    suspend fun getBookcase(searchQuery: String) : Bookcase
    suspend fun getBook(id: String) : Book
}

class NetworkBooksRepository(private val booksApiService: BooksApiService) : BooksRepository {

    override suspend fun getBookcase(searchQuery: String): Bookcase {
        return booksApiService.getBookcase(searchQuery)
    }

    override suspend fun getBook(id: String): Book {
        return booksApiService.getBook(id)
    }
}