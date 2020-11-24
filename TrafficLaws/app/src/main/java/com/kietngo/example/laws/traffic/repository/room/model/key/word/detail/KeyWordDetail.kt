package com.kietngo.example.laws.traffic.repository.room.model.key.word.detail

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "TABLE_KEYWORD_DETAIL", primaryKeys = ["ID"])
data class KeyWordDetail(
    @ColumnInfo(name ="ID") val id: Int?,
    @ColumnInfo(name ="Keyword_ID") val keyWordId : Int?,
    @ColumnInfo(name ="Violation_ID") val violationId: Long?,
    @ColumnInfo(name ="Rate") val rate: Int?,
    @ColumnInfo(name ="UpdateDay") val updateDay: String?,
    @ColumnInfo(name ="IsDelete") val isDelete: Int?,
)