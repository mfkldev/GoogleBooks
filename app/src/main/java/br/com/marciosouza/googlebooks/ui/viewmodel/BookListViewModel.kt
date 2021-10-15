package br.com.marciosouza.googlebooks.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.marciosouza.googlebooks.model.Volume
import br.com.marciosouza.googlebooks.usecase.BookUseCase
import kotlinx.coroutines.launch

class BookListViewModel : ViewModel() {

    private val _state = MutableLiveData<State>() //??
    val state: LiveData<State> = _state

    private val bookUseCase = BookUseCase()

    fun loadInitialBooks() {
        if (_state.value is State.Loaded) return
        search("Dominando Android")
    }

    fun search(query: String) {
        viewModelScope.launch {
            _state.value = State.Loading

            val result = bookUseCase.searchBooks(query)

            if (result?.items == null) {
                _state.value = State.Error(Exception("Error loading books"), false)
            } else {
                _state.value = State.Loaded(result.items)
            }
        }
    }

    sealed class State {
        object Loading : State()
        data class Loaded(val items: List<Volume>) : State()
        data class Error(val e: Throwable, var hasConsumed: Boolean) : State()
    }
}
