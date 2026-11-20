package com.example.animedex.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.animedex.data.local.dao.AnimeDao
import com.example.animedex.data.local.entity.AnimeCharacter
import com.example.animedex.data.local.entity.AnimeCompany
import com.example.animedex.data.local.entity.AnimeEntity
import com.example.animedex.data.local.entity.AnimeGenreCrossRef
import com.example.animedex.data.local.entity.AnimeStaff
import com.example.animedex.data.local.entity.CharacterEntity
import com.example.animedex.data.local.entity.CharacterVoiceActor
import com.example.animedex.data.local.entity.CompanyEntity
import com.example.animedex.data.local.entity.GenreEntity
import com.example.animedex.data.local.entity.PersonEntity

@Database(
    entities = [
        AnimeEntity::class,
        GenreEntity::class,
        CompanyEntity::class,
        CharacterEntity::class,
        PersonEntity::class,
        AnimeGenreCrossRef::class,
        AnimeCompany::class,
        AnimeCharacter::class,
        AnimeStaff::class,
        CharacterVoiceActor::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AnimeDatabase : RoomDatabase() {

    abstract fun animeDao(): AnimeDao

    companion object {
        @Volatile
        private var INSTANCE: AnimeDatabase? = null

        fun getDatabase(context: Context): AnimeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AnimeDatabase::class.java,
                    "anime.db"
                )
                    .createFromAsset("databases/anime.db")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
