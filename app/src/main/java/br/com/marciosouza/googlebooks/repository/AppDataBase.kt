package br.com.marciosouza.googlebooks.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Book::class], //??
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase(){ //Abstract pq o Room completa a implementação dela em tempo de compilação

    abstract fun getBookDao(): BookDao

    companion object {
        private var instance: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, AppDataBase::class.java,"appDb").build()
            }
            return instance!!
        }
    }
}