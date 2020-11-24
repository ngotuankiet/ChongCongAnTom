package com.kietngo.example.laws.traffic.repository.room.model.noticeboard

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "TABLE_NOTICE_BOARD", primaryKeys =["ID"] )
data class NoticeBoard(
        @ColumnInfo(name ="ID") val id: Int?,
        @ColumnInfo(name ="Type_ID") val typeId: Int?,
        @ColumnInfo(name ="Name") val name: String?,
        @ColumnInfo(name ="NameEN") val nameEn: String?,
        @ColumnInfo(name ="Detail") val detail: String?,
        @ColumnInfo(name ="Icon") val icon: String?,
        @ColumnInfo(name ="UpdateDay") val updateDay: String?,
        @ColumnInfo(name ="IsDelete") val isDelete: Int?
)