package com.kietngo.example.laws.traffic.repository.room.model.bookmarktype

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "TABLE_BOOKMARK_TYPE", primaryKeys = ["Type_ID"])
data class BookMarkType(
    @ColumnInfo(name ="Type_ID") val typeId: Int?,
    @ColumnInfo(name ="Type_Name") val typeName : String?,
    @ColumnInfo(name ="IsDelete") val isDelete: Int?
)