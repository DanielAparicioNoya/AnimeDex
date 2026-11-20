package com.example.animedex.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "anime_staff",
    primaryKeys = ["anime_id", "person_id"],
    foreignKeys = [
        ForeignKey(
            entity = AnimeEntity::class,
            parentColumns = ["anime_id"],
            childColumns = ["anime_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PersonEntity::class,
            parentColumns = ["person_id"],
            childColumns = ["person_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["person_id"])
    ]
)
data class AnimeStaff(
    @ColumnInfo(name = "anime_id")
    val animeId: Int,

    @ColumnInfo(name = "person_id")
    val personId: Int,

    @ColumnInfo(name = "role")
    val role: String
)
