package br.com.marciosouza.googlebooks.repository

import android.content.Context
import br.com.marciosouza.googlebooks.model.Volume
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookRepository(context: Context) {

    private val dao: BookDao = AppDataBase.getDatabase(context).getBookDao()

    suspend fun save(volume: Volume){
        dao.save(BookVolumeMapper.volumeToBook(volume))
    }

    suspend fun detele(volume: Volume){
        dao.delete(BookVolumeMapper.volumeToBook(volume))
    }

    suspend fun isFavorite(id: String): Boolean {
        return dao.isFavorites(id) > 0
    }

    fun allFavorites(): Flow<List<Volume>>{
        return dao.alLFavorites()
            .map{bookList ->
                bookList.map{book ->
                    BookVolumeMapper.bookToVolume(book)
                }
            }
    }
}
