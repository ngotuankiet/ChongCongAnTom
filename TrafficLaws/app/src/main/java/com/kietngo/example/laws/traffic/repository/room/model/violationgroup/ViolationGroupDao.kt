package com.kietngo.example.laws.traffic.repository.room.model.violationgroup

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface ViolationGroupDao {

    @Query("SELECT * FROM TABLE_VIOLATION_GROUP")
    fun getAllViolationGroup(): LiveData<List<ViolationGroup>>
}