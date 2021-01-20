package com.kietngo.example.laws.traffic.ui.index

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kietngo.example.laws.traffic.repository.repository.BookMarkRepository
import com.kietngo.example.laws.traffic.repository.repository.BookmarkTypeRepository
import com.kietngo.example.laws.traffic.repository.repository.ViolationRepository
import com.kietngo.example.laws.traffic.repository.room.model.AppDatabase
import com.kietngo.example.laws.traffic.repository.room.model.bookmarktype.BookMarkType
import com.kietngo.example.laws.traffic.ui.model.BookMarkTypeUI
import com.kietngo.example.laws.traffic.ui.model.BookmarkUI
import com.kietngo.example.laws.traffic.ui.model.ViolationUI

class IndexViewModel(
    val context : Context
): ViewModel() {
//    private  val searchChannel = ConflatedBroadcastChannel<String>()

    private val violationRepository : ViolationRepository
    private val bookMarkRepository: BookMarkRepository
    private val bookMarkTypeRepository: BookmarkTypeRepository

    init {
        val dao = AppDatabase.getDatabase(context, viewModelScope)
        val violationDao = dao.violationDao()
        violationRepository = ViolationRepository(violationDao)

        val bookMarkDao  = dao.bookMarkDao()
        bookMarkRepository = BookMarkRepository(bookMarkDao)

        val bookMarkTypeDao = dao.bookMarkTypeDao()
        bookMarkTypeRepository = BookmarkTypeRepository(bookMarkTypeDao)


    }

    fun getViolationWithId(id: Int): LiveData<ViolationUI>{
        val violation = violationRepository.getViolation(id)
        return Transformations.map(violation){
            ViolationUI(
                violation = it,
                onClick = {
                    //TODO: Share ...
                }
            )
        }
    }

    fun getBookMarkWithViolationId(id: Int): LiveData<BookmarkUI> {
        val bookmark = bookMarkRepository.getBookMarkWithViolationId(id)
        return Transformations.map(bookmark){
            BookmarkUI(
                    bookmark = it,
                    onClick = {
                        //TODO:
                    }
            )
        }
    }

    fun getBookMarkTypeWithViolationTypeId(id: Int) : LiveData<BookMarkTypeUI>{
        val bookMarkType = bookMarkTypeRepository.getNameBookMarkTypeWithTypeId(id)
        return Transformations.map(bookMarkType){
            BookMarkTypeUI(
                    bookMarkType = it,
                    onClick = {
                        //TODO:...
                    }
            )
        }
    }

}