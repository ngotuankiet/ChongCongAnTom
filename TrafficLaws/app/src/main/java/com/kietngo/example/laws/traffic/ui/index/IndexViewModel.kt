package com.kietngo.example.laws.traffic.ui.index

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kietngo.example.laws.traffic.repository.repository.ViolationRepository
import com.kietngo.example.laws.traffic.repository.room.model.AppDatabase
import com.kietngo.example.laws.traffic.ui.model.ViolationUI

class IndexViewModel(
    val context : Context
): ViewModel() {

    private val violationRepository : ViolationRepository

    init {
        val dao = AppDatabase.getDatabase(context,viewModelScope).violationDao()
        violationRepository = ViolationRepository(dao)
    }

    fun getViolationWithId(id: Int): LiveData<ViolationUI>{
        val violation = violationRepository.getViolation(id)
        return Transformations.map(violation){
            ViolationUI(
                violation = it,
                onClick = {
                    //TODO: Share ...
                }
            )
        }
    }

}