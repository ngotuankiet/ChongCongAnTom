package com.kietngo.example.laws.traffic.ui.model

import com.kietngo.example.laws.traffic.repository.room.model.bookmark.Bookmark

data class BookmarkUI(
        val bookmark: Bookmark,
        val onClick : () -> Unit
        )