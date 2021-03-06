package com.kietngo.example.laws.traffic.repository.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.kietngo.example.laws.traffic.repository.room.model.violationgroup.ViolationGroup
import com.kietngo.example.laws.traffic.repository.room.model.violationgroup.ViolationGroupDao
import com.kietngo.example.laws.traffic.ui.model.ViolationGroupUI

class ViolationGroupRepository(private val violationGroupDao: ViolationGroupDao) {

    fun getAllViolationGroupUI(): LiveData<List<ViolationGroup>> = violationGroupDao.getAllViolationGroup()

    fun getViolationGroupWithGroupID(groupID : Int) : LiveData<ViolationGroup> = violationGroupDao.getViolationGroupID(groupID)
}