package com.kietngo.example.laws.traffic.repository.room.model.bookmark

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TABLE_BOOKMARK", primaryKeys = ["ID"])
data class Bookmark(
    @ColumnInfo(name = "ID") val id : Int?,
    @ColumnInfo(name = "Type_ID") val typeId : Int?,
    @ColumnInfo(name = "Violation_ID") val violationId: Long?,
    @ColumnInfo(name = "Bookmark_ID") val bookmarkId: Long?,
    @ColumnInfo(name = "Bookmark_Code") val bookmarkCode: String?,
    @ColumnInfo(name = "Bookmark_Name") val bookmarkName: String?,
    @ColumnInfo(name = "Law_ID") val lawId: Long?,
    @ColumnInfo(name = "UpdateDay") val UpdateDay: String?,
    @ColumnInfo(name = "IsDelete") val isDelete: Int?
)