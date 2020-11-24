package com.kietngo.example.laws.traffic.ui.model

import com.kietngo.example.laws.traffic.repository.room.model.violationgroup.ViolationGroup

data class ViolationGroupUI(
        val violationGroup: ViolationGroup,
        val onClick: () -> Unit
)