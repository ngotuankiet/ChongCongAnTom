package com.kietngo.example.laws.traffic.repository.room.model.violation

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "TABLE_VIOLATION", primaryKeys = ["ID"])
data class Violation(
    @ColumnInfo(name ="ID") val id: Int?,
    @ColumnInfo(name ="Name") val name: String?,
    @ColumnInfo(name ="Object") val objectTraffic: String?,
    @ColumnInfo(name ="Fines") val fines :String?,
    @ColumnInfo(name ="Additional_Penalties") val additionalPenalties : String?,
    @ColumnInfo(name ="Remedial_Measures") val remedialMeasures: String?,
    @ColumnInfo(name ="Other_Penalties") val otherPenalties : String?,
    @ColumnInfo(name ="Type_ID") val typeId: Int?,
    @ColumnInfo(name ="Group_ID") val groupId: Int?,
    @ColumnInfo(name ="Icon") val icon : String?,
    @ColumnInfo(name ="Keyword") val keyWord: String?,
    @ColumnInfo(name ="UpdateDay") val updateDay: String?,
    @ColumnInfo(name ="IsDelete") val isDelete: Int?
)