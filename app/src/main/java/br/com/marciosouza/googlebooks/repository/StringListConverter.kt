package br.com.marciosouza.googlebooks.repository

import androidx.room.TypeConverter


class StringListConverter { //??

    @TypeConverter
    fun stringToList(string: String?): List<String>? = string?.split(',')

    @TypeConverter
    fun listToString(list: List<String>): String? = list?.joinToString(",")
}