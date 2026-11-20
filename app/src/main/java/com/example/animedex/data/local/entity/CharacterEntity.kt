package com.example.animedex.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class CharacterEntity(
    @PrimaryKey
    @ColumnInfo(name = "character_id")
    val characterId: Int,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "image_url")
    val imageUrl: String?
)
