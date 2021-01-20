package com.kietngo.example.laws.traffic.repository.room.model.bookmarktype

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.kietngo.example.laws.traffic.repository.room.model.bookmarktype.BookMarkType

@Dao
interface BookmarkTypeDao {
    /*get all bookmark Type*/
    @Query("SELECT * FROM TABLE_BOOKMARK_TYPE")
    fun getAllBookmarkType(): List<BookMarkType>

    /*get bookmark type with type id*/
    @Query("SELECT * FROM TABLE_BOOKMARK_TYPE WHERE Type_ID = :typeId")
    fun getBookmarkTypeWithTypeId(typeId: Int) : LiveData<BookMarkType>
}