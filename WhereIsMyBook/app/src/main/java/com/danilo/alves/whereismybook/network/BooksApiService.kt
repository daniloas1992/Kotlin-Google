package com.danilo.alves.whereismybook.network

import com.danilo.alves.whereismybook.model.Book
import com.danilo.alves.whereismybook.model.Bookcase
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksApiService {

    //https://www.googleapis.com/books/v1/volumes?q=jazz+history
    @GET("volumes")
    suspend fun getBookcase(@Query("q") searchQuery: String): Bookcase

    //https://www.googleapis.com/books/v1/volumes/<volume_id>
    @GET("volumes/{id}")
    suspend fun getBook(@Path("id") id: String): Book
}