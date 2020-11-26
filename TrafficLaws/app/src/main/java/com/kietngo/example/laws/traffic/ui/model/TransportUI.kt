package com.kietngo.example.laws.traffic.ui.model

import com.kietngo.example.laws.traffic.repository.room.model.transport.type.TransportType

data class TransportUI(
        val transportType: TransportType,
        val onClick: () -> Unit
)