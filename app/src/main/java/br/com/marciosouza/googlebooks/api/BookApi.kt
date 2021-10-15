package br.com.marciosouza.googlebooks.api

import br.com.marciosouza.googlebooks.model.SearchResult
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.converter.gson.GsonConverterFactory

interface BookApi {

    @GET("volumes")
    suspend fun searchBooks(@Query("q") q: String)
            : Response<SearchResult>

    companion object {

        private const val BASE_URL = "https://www.googleapis.com/books/v1/"

        fun create(): BookApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BookApi::class.java)
        }
    }
}
