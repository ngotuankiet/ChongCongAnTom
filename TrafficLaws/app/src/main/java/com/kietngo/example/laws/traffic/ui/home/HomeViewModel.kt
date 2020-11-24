package com.kietngo.example.laws.traffic.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kietngo.example.laws.traffic.repository.room.model.violationgroup.ViolationGroup
import com.kietngo.example.laws.traffic.ui.model.ViolationGroupUI

class HomeViewModel: ViewModel() {

    val listViolationGroup = MutableLiveData<List<ViolationGroupUI>>()

    init {
        listViolationGroup.postValue(fakeListViolation())
    }

    private fun fakeListViolation(): List<ViolationGroupUI>{
        val list = ArrayList<ViolationGroupUI>()
        for (i in 0.. 30){
            val violationGroup = ViolationGroup(
                    i,
                    "test",
                    1,
                    1,
                    "test",
                    1,
                    "test"
            )
            val violationGroupUI = ViolationGroupUI(violationGroup,{})
            list.add(violationGroupUI)
        }
        return list
    }

}