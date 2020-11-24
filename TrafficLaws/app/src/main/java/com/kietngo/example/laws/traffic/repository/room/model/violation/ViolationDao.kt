package com.kietngo.example.laws.traffic.repository.room.model.violation

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface ViolationDao {

    /*Get all Violation */
    @Query("SELECT * FROM TABLE_VIOLATION")
    fun getALlViolation(): List<Violation>

    /*get violation with id*/
    @Query("SELECT * FROM TABLE_VIOLATION WHERE ID= :id")
    fun getViolationWithId(id :Int): LiveData<Violation>
}