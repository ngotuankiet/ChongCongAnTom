package com.kietngo.example.laws.traffic.repository.repository

import androidx.lifecycle.LiveData
import com.kietngo.example.laws.traffic.repository.room.model.bookmarktype.BookMarkType
import com.kietngo.example.laws.traffic.repository.room.model.bookmarktype.BookmarkTypeDao

class BookmarkTypeRepository constructor(
        private val bookmarkTypeDao: BookmarkTypeDao
) {
    fun getNameBookMarkTypeWithTypeId(id: Int): LiveData<BookMarkType> = bookmarkTypeDao.getBookmarkTypeWithTypeId(id)
}