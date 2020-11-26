package com.kietngo.example.laws.traffic.repository.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.kietngo.example.laws.traffic.repository.room.model.transport.type.TransportType
import com.kietngo.example.laws.traffic.repository.room.model.transport.type.TransportTypeDao
import com.kietngo.example.laws.traffic.ui.model.TransportUI

class TransportRepository(private val transportTypeDao: TransportTypeDao) {
    fun getAllTransport() : LiveData<List<TransportType>> = transportTypeDao.getAllTransportType()
}