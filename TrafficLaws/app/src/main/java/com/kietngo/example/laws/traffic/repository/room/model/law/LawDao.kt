package com.kietngo.example.laws.traffic.repository.room.model.law

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Query
import com.kietngo.example.laws.traffic.repository.room.model.law.Law

@Dao
interface LawDao {
    /*get all law */
    @Query("SELECT * FROM TABLE_LAW")
    fun getAllLaws(): List<Law>

    /*get law with id*/
//    @Query("SELECT * FROM TABLE_LAW WHERE Law_ID= :lawId")
//    fun getLawWithId(lawId: Int): LiveData<Law>
}