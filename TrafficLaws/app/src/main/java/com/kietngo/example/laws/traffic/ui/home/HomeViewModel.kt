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

    private val listViolation : LiveData<List<Violation>>
    val listViolationUI : LiveData<List<ViolationUI>>

    private val listTransport : LiveData<List<TransportType>>
    val listTransportUI : LiveData<List<TransportUI>>


    private val _navigateViolation = MutableLiveData<Event<Boolean>>()
    val navigateViolation : LiveData<Event<Boolean>> = _navigateViolation

    private val _navigateIndex = MutableLiveData<Event<NavDirections>>()
    val navigateIndex : LiveData<Event<NavDirections>> = _navigateIndex

    //go to search fragment
    private val _navigateSearch = MutableLiveData<Event<NavDirections>>()
    val navigateSearch : LiveData<Event<NavDirections>> = _navigateSearch

    private val _btnSearch = MutableLiveData<ButtonUI>().apply {
        value = ButtonUI(
            onClick = {
                val action = HomeFragmentDirections.actionHomeFragmentToShareFragment()
                _navigateSearch.postValue(Event(action))
            }
        )
    }
    val btnSearch : LiveData<ButtonUI> = _btnSearch
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
        listViolation = violationRepository.getAllViolation()
        listViolationUI = Transformations.map(listViolation){
            it.map { violation ->
                ViolationUI(
                        violation = violation,
                        onClick = {
                            val id = violation.id
                            if(id != null){
                                val action = HomeFragmentDirections.actionHomeFragmentToIndexFragment(id)
                                _navigateIndex.postValue(Event(action))
                            }
                        }
                )
            }
        }

        listTransport = transportRepository.getAllTransport()
        listTransportUI  = Transformations.map(listTransport) {
            it.map { transportType ->
                TransportUI(
                    transportType = transportType,
                    onClick = {
                      //  _navigateViolation.postValue(Event(true))
                    }
                )
            }
        }

    }


}