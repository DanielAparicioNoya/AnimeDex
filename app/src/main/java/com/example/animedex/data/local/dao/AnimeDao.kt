package com.example.animedex.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.animedex.data.local.relation.AnimeWithGenres
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {
    @Transaction
    @Query("SELECT * FROM anime")
    fun getAllAnimes(): Flow<List<AnimeWithGenres>>
}
