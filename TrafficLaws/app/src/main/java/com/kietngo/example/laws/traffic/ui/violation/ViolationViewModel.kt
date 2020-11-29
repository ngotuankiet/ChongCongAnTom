package com.kietngo.example.laws.traffic.ui.violation

import android.content.Context
import androidx.lifecycle.*

import androidx.navigation.NavDirections
import com.kietngo.example.laws.traffic.repository.Event
import com.kietngo.example.laws.traffic.repository.repository.ViolationGroupRepository
import com.kietngo.example.laws.traffic.repository.repository.ViolationRepository
import com.kietngo.example.laws.traffic.repository.room.model.AppDatabase
import com.kietngo.example.laws.traffic.repository.room.model.violation.Violation
import com.kietngo.example.laws.traffic.repository.room.model.violationgroup.ViolationGroupDao
import com.kietngo.example.laws.traffic.ui.model.ViolationGroupUI
import com.kietngo.example.laws.traffic.ui.model.ViolationUI
import timber.log.Timber

class ViolationViewModel(context: Context): ViewModel() {
    private val violationRepository: ViolationRepository
    private val violationGroupRepository : ViolationGroupRepository

    private val listViolation : LiveData<List<Violation>>
    val listViolationUI : LiveData<List<ViolationUI>>

    //TODO: Go to Index fragment
    private val _navigateIndex = MutableLiveData<Event<NavDirections>>()
    val navigateIndex : LiveData<Event<NavDirections>> = _navigateIndex


    init {
        val violationDao = AppDatabase.getDatabase(context,viewModelScope).violationDao()
        violationRepository = ViolationRepository(violationDao)

        val violationGroupDao = AppDatabase.getDatabase(context, viewModelScope).violationGroupDao()
        violationGroupRepository = ViolationGroupRepository(violationGroupDao)

        listViolation = violationRepository.getAllViolation()
        listViolationUI = Transformations.map(listViolation) {
            it.map { violation ->
                ViolationUI (
                    violation = violation,
                    onClick = {
                        //TODO: Navigate Chi tiet violation
                    }
                )
            }
        }

    }

    fun getAllListViolationUIWithId(groupId: Int) : LiveData<List<ViolationUI>>{
        val list = violationRepository.getAllViolationWithId(groupId)
        return Transformations.map(list){
            it.map { violation ->
                ViolationUI(
                        violation = violation
                ) {
                    if (violation.id != null) {
                        val action =
                            ViolationFragmentDirections.actionViolationFragmentToIndexFragment2(
                                violation.id
                            )
                        _navigateIndex.postValue(Event(action))
                        Timber.d("navigate to index")
                    }
                }
            }
        }
    }

    fun getViolationGroupUiWithGroupId(groupId: Int) : LiveData<ViolationGroupUI>{
        val violationGroup = violationGroupRepository.getViolationGroupWithGroupID(groupId)
        return Transformations.map(violationGroup){
            ViolationGroupUI(
                    violationGroup = it,
                    onClick = {

                    }
            )
        }
    }
}