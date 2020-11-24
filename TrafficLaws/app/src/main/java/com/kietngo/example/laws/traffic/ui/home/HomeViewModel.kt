package com.kietngo.example.laws.traffic.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kietngo.example.laws.traffic.repository.repository.ViolationGroupRepository
import com.kietngo.example.laws.traffic.repository.repository.ViolationRepository
import com.kietngo.example.laws.traffic.repository.room.model.AppDatabase
import com.kietngo.example.laws.traffic.repository.room.model.violation.Violation
import com.kietngo.example.laws.traffic.repository.room.model.violationgroup.ViolationGroup
import com.kietngo.example.laws.traffic.ui.model.ViolationGroupUI
import com.kietngo.example.laws.traffic.ui.model.ViolationUI

class HomeViewModel constructor(
        private val application: Application
) : ViewModel() {

    private val violationGroupRepository : ViolationGroupRepository
    private val violationRepository: ViolationRepository

    val listViolationGroupUI : LiveData<List<ViolationGroupUI>>
    val listViolationUI : LiveData<List<ViolationUI>>

    init {
        val violationGroupDao = AppDatabase.getDatabase(application,viewModelScope).violationGroupDao()
        val violationDao = AppDatabase.getDatabase(application,viewModelScope).violationDao()

        violationGroupRepository = ViolationGroupRepository(violationGroupDao)
        violationRepository = ViolationRepository(violationDao)

        listViolationGroupUI = violationGroupRepository.getAllViolationGroupUI()
        listViolationUI = violationRepository.getAllListViolationUI()
    }


}