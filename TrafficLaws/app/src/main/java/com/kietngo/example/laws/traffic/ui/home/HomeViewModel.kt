package com.kietngo.example.laws.traffic.ui.home

import android.app.Application
import android.widget.Button
import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.kietngo.example.laws.traffic.repository.Event
import com.kietngo.example.laws.traffic.repository.repository.TransportRepository
import com.kietngo.example.laws.traffic.repository.repository.ViolationGroupRepository
import com.kietngo.example.laws.traffic.repository.repository.ViolationRepository
import com.kietngo.example.laws.traffic.repository.room.model.AppDatabase
import com.kietngo.example.laws.traffic.repository.room.model.transport.type.TransportType
import com.kietngo.example.laws.traffic.repository.room.model.violation.Violation
import com.kietngo.example.laws.traffic.repository.room.model.violationgroup.ViolationGroup
import com.kietngo.example.laws.traffic.ui.model.ButtonUI
import com.kietngo.example.laws.traffic.ui.model.TransportUI
import com.kietngo.example.laws.traffic.ui.model.ViolationGroupUI
import com.kietngo.example.laws.traffic.ui.model.ViolationUI

class HomeViewModel constructor(
        private val application: Application
) : ViewModel() {

    private val violationGroupRepository : ViolationGroupRepository
    private val violationRepository: ViolationRepository
    private val transportRepository : TransportRepository

    private val listViolationGroup: LiveData<List<ViolationGroup>>
    val listViolationGroupUI : LiveData<List<ViolationGroupUI>>
    val listViolationUI : LiveData<List<ViolationUI>>
    private val listTransport : LiveData<List<TransportType>>
    val listTransportUI : LiveData<List<TransportUI>>

    //TODO: onclick violation
    private val _btnViolation = MutableLiveData<ButtonUI>().apply {
        value = ButtonUI(
            onClick = {
                _navigateViolation.postValue(Event(true))
            }
        )
    }
    val btnViolation : LiveData<ButtonUI> = _btnViolation

    private val _navigateViolation = MutableLiveData<Event<Boolean>>()
    val navigateViolation : LiveData<Event<Boolean>> = _navigateViolation

    init {
        val violationGroupDao = AppDatabase.getDatabase(application,viewModelScope).violationGroupDao()
        val violationDao = AppDatabase.getDatabase(application,viewModelScope).violationDao()
        val transportDao = AppDatabase.getDatabase(application,viewModelScope).transportTypeDao()

        violationGroupRepository = ViolationGroupRepository(violationGroupDao)
        violationRepository = ViolationRepository(violationDao)
        transportRepository = TransportRepository(transportDao)

        listViolationGroup = violationGroupRepository.getAllViolationGroupUI()
        listViolationGroupUI = Transformations.map(listViolationGroup){
            it.map { violationGroup ->
                ViolationGroupUI(
                        violationGroup = violationGroup,
                        onClick = {
                            _navigateViolation.postValue(Event(true))
                        }
                )
            }
        }
        listViolationUI = violationRepository.getAllListViolationUI()

        listTransport = transportRepository.getAllTransport()
        listTransportUI  = Transformations.map(listTransport) {
            it.map { transportType ->
                TransportUI(
                    transportType = transportType,
                    onClick = {
                        _navigateViolation.postValue(com.kietngo.example.laws.traffic.repository.Event(true))
                    }
                )
            }
        }

    }


}