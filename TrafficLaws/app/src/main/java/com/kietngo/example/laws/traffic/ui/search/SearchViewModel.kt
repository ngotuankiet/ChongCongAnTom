package com.kietngo.example.laws.traffic.ui.search

import android.content.Context
import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.kietngo.example.laws.traffic.repository.Event
import com.kietngo.example.laws.traffic.repository.repository.ViolationRepository
import com.kietngo.example.laws.traffic.repository.room.model.AppDatabase
import com.kietngo.example.laws.traffic.repository.room.model.violation.Violation
import com.kietngo.example.laws.traffic.ui.model.ButtonUI
import com.kietngo.example.laws.traffic.ui.model.ViolationUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class SearchViewModel constructor(
        val context: Context
): ViewModel() {
    private val violationRepository: ViolationRepository
    val listViolationUI: LiveData<List<ViolationUI>>

    private val _navigateIndex = MutableLiveData<Event<NavDirections>>()
    val navigateIndex : LiveData<Event<NavDirections>> = _navigateIndex

    init {
        val violationDao = AppDatabase.getDatabase(context, viewModelScope).violationDao()
        violationRepository = ViolationRepository(violationDao)


        listViolationUI = Transformations.map(violationRepository.getAllViolation()){
            it.map { violation ->
                ViolationUI(
                        violation = violation,
                        onClick = {
                             val id = violation.id
                            if(id != null){
                                val action = SearchFragmentDirections.actionShareFragmentToIndexFragment(id)
                                _navigateIndex.postValue(Event(action))
                            }
                        }
                )
            }
        }

    }

}