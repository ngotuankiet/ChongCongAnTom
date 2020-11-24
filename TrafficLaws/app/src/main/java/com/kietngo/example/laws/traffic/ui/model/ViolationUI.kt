package com.kietngo.example.laws.traffic.ui.model

import com.kietngo.example.laws.traffic.repository.room.model.violation.Violation


data class ViolationUI(
        val violation: Violation,
        val onClick: () -> Unit
)