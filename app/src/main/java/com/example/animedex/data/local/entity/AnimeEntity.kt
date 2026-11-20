package com.example.animedex.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "anime",
    indices = [
        Index(value = ["rank"]),
        Index(value = ["type"])
    ]
)
data class AnimeEntity(
    @PrimaryKey
    @ColumnInfo(name = "anime_id")
    val animeId: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "score")
    val score: Double,

    @ColumnInfo(name = "rank")
    val rank: Int,

    @ColumnInfo(name = "popularity")
    val popularity: Int,

    @ColumnInfo(name = "members")
    val members: Int,

    @ColumnInfo(name = "synopsis")
    val synopsis: String?,

    @ColumnInfo(name = "start_date")
    val startDate: String?,

    @ColumnInfo(name = "end_date")
    val endDate: String?,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "episodes")
    val episodes: Int?,

    @ColumnInfo(name = "image_url")
    val imageUrl: String
)
