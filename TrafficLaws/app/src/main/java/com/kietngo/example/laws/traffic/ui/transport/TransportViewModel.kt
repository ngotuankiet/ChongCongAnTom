package com.kietngo.example.laws.traffic.ui.transport

import android.content.Context
import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.kietngo.example.laws.traffic.repository.Event
import com.kietngo.example.laws.traffic.repository.repository.TransportRepository
import com.kietngo.example.laws.traffic.repository.repository.ViolationGroupRepository
import com.kietngo.example.laws.traffic.repository.room.model.AppDatabase
import com.kietngo.example.laws.traffic.ui.model.TransportUI
import com.kietngo.example.laws.traffic.ui.model.ViolationGroupUI

class TransportViewModel constructor(val context: Context): ViewModel() {
    private val violationGroupRepository : ViolationGroupRepository

    val listViolationGroupUI : LiveData<List<ViolationGroupUI>>
    private val _navigateViolation = MutableLiveData<Event<Int>>()
    val navigateViolation : LiveData<Event<Int>> = _navigateViolation

    init {
        val violationGroupUI = AppDatabase.getDatabase(context,viewModelScope).violationGroupDao()
        violationGroupRepository = ViolationGroupRepository(violationGroupUI)

        listViolationGroupUI = Transformations.map(violationGroupRepository.getAllViolationGroupUI()){
            it.map { violationGroup ->
                ViolationGroupUI(
                    violationGroup = violationGroup,
                    onClick = {
                        val groupSortId = violationGroup.groupSort
                        if(groupSortId != null){
                            _navigateViolation.postValue(Event(groupSortId))
                        }
                    }
                )
            }
        }
    }
}