package com.kietngo.example.laws.traffic.repository.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.kietngo.example.laws.traffic.repository.room.model.violation.Violation
import com.kietngo.example.laws.traffic.repository.room.model.violation.ViolationDao
import com.kietngo.example.laws.traffic.ui.model.ViolationUI

class ViolationRepository(private val violationDao: ViolationDao) {

    fun getAllViolation(): LiveData<List<Violation>> = violationDao.getALlViolation()

    fun getAllViolationWithId(groupId: Int) : LiveData<List<Violation>> = violationDao.getViolationWithId(groupId)

}