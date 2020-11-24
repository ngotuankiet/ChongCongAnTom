package com.kietngo.example.laws.traffic.repository.room.model.transport.type

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface TransportTypeDao {
    /*get all Transport Type */
    @Query("SELECT * FROM TABLE_TRANSPORT_TYPE")
    fun getAllTransportType(): List<TransportType>

    /*get transport type with id*/
//    @Query("SELECT * FROM TABLE_TRANSPORT_TYPE WHERE Type_ID = :typeId")
//    fun getTransportTypeWithId(typeId: Int): LiveData<TransportType>
}