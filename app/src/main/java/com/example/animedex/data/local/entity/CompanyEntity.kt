package com.example.animedex.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "company")
data class CompanyEntity(
    @PrimaryKey
    @ColumnInfo(name = "company_id")
    val companyId: Int,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "company_type")
    val companyType: String,

    @ColumnInfo(name = "image_url")
    val imageUrl: String?
)
