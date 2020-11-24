package com.kietngo.example.laws.traffic.repository.room.model.keyword

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "TABLE_KEYWORD", primaryKeys = ["ID"])
data class KeyWord(
    @ColumnInfo(name = "ID") val id: Int?,
    @ColumnInfo(name = "Name") val name: String?,
    @ColumnInfo(name = "NameEN") val nameEn: String?,
    @ColumnInfo(name = "UpdateDay") val updateDay: String?,
    @ColumnInfo(name = "IsDelete") val isDelete: Int?
)