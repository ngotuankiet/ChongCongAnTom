package com.kietngo.example.laws.traffic.repository.repository

import androidx.lifecycle.LiveData
import com.kietngo.example.laws.traffic.repository.room.model.bookmark.Bookmark
import com.kietngo.example.laws.traffic.repository.room.model.bookmark.BookmarkDao

class BookMarkRepository constructor(
        private val bookmarkDao: BookmarkDao
){
    fun getBookMarkWithViolationId(id: Int) : LiveData<Bookmark> = bookmarkDao.getBookmarkWithId(id)
}