package com.example.animedex.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "person",
    indices = [
        Index(value = ["person_type"])
    ]
)
data class PersonEntity(
    @PrimaryKey
    @ColumnInfo(name = "person_id")
    val personId: Int,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "person_type")
    val personType: String,

    @ColumnInfo(name = "image_url")
    val imageUrl: String?
)
