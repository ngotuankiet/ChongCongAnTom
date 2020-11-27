package com.kietngo.example.laws.traffic.ui.share

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShareViewModel: ViewModel() {

    val shareViolationGroupId = MutableLiveData<Int>()
    val shareViolationGroupIdToGet : LiveData<Int> = shareViolationGroupId
}