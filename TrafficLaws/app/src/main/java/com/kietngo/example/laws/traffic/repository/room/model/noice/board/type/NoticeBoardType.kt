package com.kietngo.example.laws.traffic.repository.room.model.noice.board.type

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "TABLE_NOTICE_BOARD_TYPE", primaryKeys = ["Type_ID"])
data class NoticeBoardType(
    @ColumnInfo(name ="Type_ID") val typeId: Int?,
    @ColumnInfo(name ="Type_Name") val typeName : String?,
    @ColumnInfo(name ="Icon") val icon: String?,
    @ColumnInfo(name ="IsDelete") val isDelete : Int?,

)