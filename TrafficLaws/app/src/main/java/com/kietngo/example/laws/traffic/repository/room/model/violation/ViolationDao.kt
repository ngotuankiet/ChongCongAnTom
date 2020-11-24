package com.kietngo.example.laws.traffic.repository.room.model.violation

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface ViolationDao {

    /*Get all Violation */
    @Query("SELECT * FROM TABLE_VIOLATION")
    fun getALlViolation(): LiveData<List<Violation>>

    /*get violation with group id*/
    @Query("SELECT * FROM TABLE_VIOLATION WHERE Group_ID LIKE :groupID")
    fun getViolationWithId(groupID :Int): LiveData<List<Violation>>
}