package com.kietngo.example.laws.traffic.ui.transport

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kietngo.example.laws.traffic.repository.repository.TransportRepository
import com.kietngo.example.laws.traffic.repository.repository.ViolationGroupRepository
import com.kietngo.example.laws.traffic.repository.room.model.AppDatabase
import com.kietngo.example.laws.traffic.ui.model.TransportUI
import com.kietngo.example.laws.traffic.ui.model.ViolationGroupUI

class TransportViewModel constructor(val context: Context): ViewModel() {
    private val violationGroupRepository : ViolationGroupRepository

    val listViolationGroupUI : LiveData<List<ViolationGroupUI>>


    init {
        val violationGroupUI = AppDatabase.getDatabase(context,viewModelScope).violationGroupDao()
        violationGroupRepository = ViolationGroupRepository(violationGroupUI)

        listViolationGroupUI = Transformations.map(violationGroupRepository.getAllViolationGroupUI()){
            it.map { violationGroup ->
                ViolationGroupUI(
                    violationGroup = violationGroup,
                    onClick = {
                        //TODO:..
                    }
                )
            }
        }
    }
}