package br.com.marciosouza.googlebooks.repository

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("SELECT * FROM Book")
    fun alLFavorites(): Flow<List<Book>> //??

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(book: Book): Long //suspend fun para poder ser usado em uma thread separada

    @Delete
    suspend fun delete(vararg book: Book): Int //vararg para aceitar mais de um livro

    @Query("SELECT COUNT(id) FROM Book WHERE id = :id") //Nao é flow pq nao retorna
    suspend fun isFavorites(id: String): Int
}