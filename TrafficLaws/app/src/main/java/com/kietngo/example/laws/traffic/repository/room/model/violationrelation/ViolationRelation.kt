package com.kietngo.example.laws.traffic.repository.room.model.violationrelation

import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity(tableName = "TABLE_VIOLATION_RELATION", primaryKeys = ["ID"])
data class ViolationRelation(
    @ColumnInfo(name ="ID") val id: Int?,
    @ColumnInfo(name ="Violation_ID") val violationId: Long?,
    @ColumnInfo(name ="R_Violation_ID") val rViolationId: Long?,
    @ColumnInfo(name ="Relation_Type") val relationType: Int?,
    @ColumnInfo(name ="UpdateDay") val updateDay: String?,
    @ColumnInfo(name ="IsDelete") val isDelete: Int?
)