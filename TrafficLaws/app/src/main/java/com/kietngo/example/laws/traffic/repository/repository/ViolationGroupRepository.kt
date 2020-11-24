package com.kietngo.example.laws.traffic.repository.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.kietngo.example.laws.traffic.repository.room.model.violationgroup.ViolationGroup
import com.kietngo.example.laws.traffic.repository.room.model.violationgroup.ViolationGroupDao
import com.kietngo.example.laws.traffic.ui.model.ViolationGroupUI

class ViolationGroupRepository(private val violationGroupDao: ViolationGroupDao) {
    private val list = violationGroupDao.getAllViolationGroup()
    private val listViolationUI : LiveData<List<ViolationGroupUI>> = Transformations.map(list){
        it.map { violationGroup ->
            ViolationGroupUI(
                    violationGroup =  violationGroup,
                    onClick = {}
            )
        }
    }
    fun getAllViolationGroupUI(): LiveData<List<ViolationGroupUI>> = listViolationUI
}