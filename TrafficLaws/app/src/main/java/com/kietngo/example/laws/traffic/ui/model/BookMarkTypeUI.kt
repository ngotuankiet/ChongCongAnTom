package com.kietngo.example.laws.traffic.ui.model

import com.kietngo.example.laws.traffic.repository.room.model.bookmarktype.BookMarkType

data class BookMarkTypeUI(
        val bookMarkType : BookMarkType,
        val onClick: () -> Unit
)
