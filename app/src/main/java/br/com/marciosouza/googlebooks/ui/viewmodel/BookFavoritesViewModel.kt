package br.com.marciosouza.googlebooks.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import br.com.marciosouza.googlebooks.repository.BookRepository

class  BookFavoritesViewModel(
    private val repository: BookRepository
) : ViewModel() {

    val favoritesBook = repository.allFavorites().asLiveData()
}