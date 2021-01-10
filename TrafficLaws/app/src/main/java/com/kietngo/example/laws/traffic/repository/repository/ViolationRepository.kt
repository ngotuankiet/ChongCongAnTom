package com.kietngo.example.laws.traffic.repository.repository

import androidx.lifecycle.LiveData
import com.kietngo.example.laws.traffic.repository.room.model.violation.Violation
import com.kietngo.example.laws.traffic.repository.room.model.violation.ViolationDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn


class ViolationRepository(private val violationDao: ViolationDao) {

    fun getAllViolation(): LiveData<List<Violation>> = violationDao.getALlViolation()

    fun getAllViolationWithId(groupId: Int) : LiveData<List<Violation>> = violationDao.getViolationWithId(groupId)

    fun getViolation(id: Int): LiveData<Violation> = violationDao.getViolation(id)

    fun getViolationT(id: Int): Violation = violationDao.getViolationT(id)

    fun getSearchViolationWithName(name: String) : Flow<List<Violation>> {
        return violationDao.filterWithName(name)
                .flowOn(Dispatchers.Default).conflate()
    }
}