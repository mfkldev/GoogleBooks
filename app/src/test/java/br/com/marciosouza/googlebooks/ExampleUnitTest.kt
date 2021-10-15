package br.com.marciosouza.googlebooks

import br.com.marciosouza.googlebooks.usecase.BookRepository
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    suspend fun googleBooksApiTest() {

        val bookRepository = BookRepository()

        val searchResult = bookRepository.searchBooks()
        searchResult?.items?.forEach{volume ->
            println(volume)
        }
    }
}