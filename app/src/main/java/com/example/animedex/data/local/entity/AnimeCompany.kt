package com.example.animedex.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "anime_company",
    primaryKeys = ["anime_id", "company_id"],
    foreignKeys = [
        ForeignKey(
            entity = AnimeEntity::class,
            parentColumns = ["anime_id"],
            childColumns = ["anime_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CompanyEntity::class,
            parentColumns = ["company_id"],
            childColumns = ["company_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["company_id"])
    ]
)
data class AnimeCompany(
    @ColumnInfo(name = "anime_id")
    val animeId: Int,

    @ColumnInfo(name = "company_id")
    val companyId: Int,

    @ColumnInfo(name = "role")
    val role: String
)
