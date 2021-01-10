package com.kietngo.example.laws.traffic.repository.room.model.violation

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ViolationDao {

    /*Get all Violation */
    @Query("SELECT * FROM TABLE_VIOLATION")
    fun getALlViolation(): LiveData<List<Violation>>

    /*get violation with group id*/
    @Query("SELECT * FROM TABLE_VIOLATION WHERE Group_ID LIKE :groupID")
    fun getViolationWithId(groupID :Int): LiveData<List<Violation>>

    @Query("SELECT * FROM TABLE_VIOLATION WHERE ID = :id")
    fun getViolation(id: Int) : LiveData<Violation>

    @Query("SELECT * FROM TABLE_VIOLATION WHERE ID = :id")
    fun getViolationT(id: Int) : Violation

    @Query("SELECT * FROM TABLE_VIOLATION  WHERE Name LIKE '%' || :name || '%'" )
    fun filterWithName(name: String) : Flow<List<Violation>>
}