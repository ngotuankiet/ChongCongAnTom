package com.kietngo.example.laws.traffic.ui.search

import android.content.Context
import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.kietngo.example.laws.traffic.repository.Event
import com.kietngo.example.laws.traffic.repository.repository.KeyWordDetailRepository
import com.kietngo.example.laws.traffic.repository.repository.KeyWordRepository
import com.kietngo.example.laws.traffic.repository.repository.ViolationRepository
import com.kietngo.example.laws.traffic.repository.room.model.AppDatabase
import com.kietngo.example.laws.traffic.repository.room.model.key.word.detail.KeyWordDetail
import com.kietngo.example.laws.traffic.repository.room.model.keyword.KeyWord
import com.kietngo.example.laws.traffic.repository.room.model.violation.Violation
import com.kietngo.example.laws.traffic.ui.model.ButtonUI
import com.kietngo.example.laws.traffic.ui.model.ViolationUI
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import timber.log.Timber

class SearchViewModel constructor(
        val context: Context
): ViewModel() {
    private val violationRepository: ViolationRepository
    val listViolationUI: LiveData<List<ViolationUI>>

    private val keyWordRepository : KeyWordRepository
    val listKeyWord: LiveData<List<KeyWord>>

    private val keyWordDetailRepository : KeyWordDetailRepository
    val listKeyWordDetail : LiveData<List<KeyWordDetail>>

    private val _navigateIndex = MutableLiveData<Event<NavDirections>>()
    val navigateIndex : LiveData<Event<NavDirections>> = _navigateIndex

    val _violationListTest = MutableLiveData<ArrayList<ViolationUI>>()
    val violationListTest : LiveData<ArrayList<ViolationUI>> = _violationListTest


    init {
        _violationListTest.postValue(ArrayList())

        val violationDao = AppDatabase.getDatabase(context, viewModelScope).violationDao()
        violationRepository = ViolationRepository(violationDao)

        listViolationUI = Transformations.map(violationRepository.getAllViolation()){
            it.map { violation ->
                ViolationUI(
                        violation = violation,
                        onClick = {
                            val id = violation.id
                            if(id != null){
                                val action = SearchFragmentDirections.actionShareFragmentToIndexFragment(id)
                                _navigateIndex.postValue(Event(action))
                            }
                        }
                )
            }
        }

        val keyWordDao = AppDatabase.getDatabase(context, viewModelScope).keyWordDao()
        keyWordRepository = KeyWordRepository(keyWordDao)
        listKeyWord = keyWordRepository.getAllKeyWord()

        val keyWordDetailDao = AppDatabase.getDatabase(context, viewModelScope).keyWordDetailDao()
        keyWordDetailRepository = KeyWordDetailRepository(keyWordDetailDao)
        listKeyWordDetail = keyWordDetailRepository.getAllKeyWordDetail()
    }

    // bien nay co chuc nang lay gia tri moi nhat gui toi... giong query
    @ExperimentalCoroutinesApi
    private val searchChannel = ConflatedBroadcastChannel<String>()

    @ExperimentalCoroutinesApi
    @FlowPreview
    private val _listViolationFlow = searchChannel.asFlow()
            .flatMapLatest { search ->
                violationRepository.getSearchViolationWithName(search)
            }
            .catch { throwable ->
                Timber.d("error listViolationTest ${throwable.message}")
            }
            .asLiveData()

    @FlowPreview
    @ExperimentalCoroutinesApi
    val listViolationFlow = Transformations.map(_listViolationFlow){
        it.map { violationT ->
            ViolationUI(
                    violation = violationT,
                    onClick = {
                        val id = violationT.id
                        if(id != null) {
                            val action =
                                SearchFragmentDirections.actionShareFragmentToIndexFragment(id)
                            _navigateIndex.postValue(Event(action))
                        }
                    }
            )
        }
    }

    fun getViolationWithId(id: Int): LiveData<ViolationUI>{
        return Transformations.map(violationRepository.getViolation(id)){
            ViolationUI(
                violation = it,
                onClick = {
                    //TODO:
                }
            )
        }
    }

    // gui search den searchChannel
    @ExperimentalCoroutinesApi
    fun setQuerySearch(search: String){
        searchChannel.offer(search)
    }

}