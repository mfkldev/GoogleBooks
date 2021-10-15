package br.com.marciosouza.googlebooks.usecase

class BookUseCase(
    private val bookRepository: BookRepository = BookRepository()
){
    suspend fun searchBooks(query: String) = bookRepository.searchBooks(query)
}