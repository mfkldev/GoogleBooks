package br.com.marciosouza.googlebooks.usecase

import br.com.marciosouza.googlebooks.api.BookApi
import br.com.marciosouza.googlebooks.model.SearchResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookRepository(private val bookApi: BookApi = BookApi.create()) {

    suspend fun searchBooks(query: String) : SearchResult? {
        return withContext(Dispatchers.IO) {
            try {
                val searchResult = bookApi.searchBooks(query)
                if (searchResult.isSuccessful) {
                    searchResult.body()
                } else {
                    null
                }
            } catch (e: Exception){
                SearchResult(listOf())
            }
        }
    }
}
