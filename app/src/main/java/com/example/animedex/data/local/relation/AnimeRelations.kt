package com.example.animedex.data.local.relation

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.animedex.data.local.entity.AnimeEntity
import com.example.animedex.data.local.entity.AnimeGenreCrossRef
import com.example.animedex.data.local.entity.GenreEntity

data class AnimeWithGenres(
    @Embedded
    val anime: AnimeEntity,

    @Relation(
        parentColumn = "anime_id",
        entityColumn = "genre_id",
        associateBy = Junction(AnimeGenreCrossRef::class)
    )
    val genres: List<GenreEntity>
)

data class CompanyWithRole(
    @ColumnInfo(name = "company_id")
    val companyId: Int,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "company_type")
    val companyType: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String?,
    @ColumnInfo(name = "role")
    val role: String
)

data class CharacterWithVoiceActor(
    @ColumnInfo(name = "character_id")
    val characterId: Int,
    @ColumnInfo(name = "character_name")
    val characterName: String?,
    @ColumnInfo(name = "character_image")
    val characterImageUrl: String?,
    @ColumnInfo(name = "role")
    val role: String,
    @ColumnInfo(name = "person_id")
    val voiceActorId: Int?,
    @ColumnInfo(name = "va_name")
    val voiceActorName: String?,
    @ColumnInfo(name = "va_image")
    val voiceActorImageUrl: String?,
    @ColumnInfo(name = "language")
    val language: String?
)

data class StaffWithRole(
    @ColumnInfo(name = "person_id")
    val personId: Int,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "person_type")
    val personType: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String?,
    @ColumnInfo(name = "role")
    val role: String
)
