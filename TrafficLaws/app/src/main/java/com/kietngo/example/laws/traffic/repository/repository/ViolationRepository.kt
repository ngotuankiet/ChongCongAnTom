package com.kietngo.example.laws.traffic.repository.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.kietngo.example.laws.traffic.repository.room.model.violation.ViolationDao
import com.kietngo.example.laws.traffic.ui.model.ViolationUI

class ViolationRepository(private val violationDao: ViolationDao) {

    fun getAllListViolationUI() : LiveData<List<ViolationUI>> {
        val listViolation = violationDao.getALlViolation()
        return Transformations.map(listViolation){
            it.map{ violation ->
                ViolationUI(
                        violation = violation,
                        onClick = {}
                )
            }
        }
    }

    fun getALlListViolationUiWithGroupID(groupID: Int): LiveData<List<ViolationUI>>{
        val listViolationID = violationDao.getViolationWithId(groupID)
        return Transformations.map(listViolationID){
            it.map { violation ->
                ViolationUI(
                        violation = violation,
                        onClick = {}
                )
            }
        }
    }
}