package com.kietngo.example.laws.traffic.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kietngo.example.laws.traffic.base.BaseFragment
import com.kietngo.example.laws.traffic.databinding.FragmentSearchBinding
import com.kietngo.example.laws.traffic.repository.EventObserver
import com.kietngo.example.laws.traffic.repository.room.model.keyword.KeyWord
import com.kietngo.example.laws.traffic.ui.model.ViolationUI
import com.kietngo.example.laws.traffic.ui.violation.ViolationInViolationGroupAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import timber.log.Timber

class SearchFragment : BaseFragment(), SearchView.OnQueryTextListener {
    private lateinit var  binding : FragmentSearchBinding
    private val viewModel : SearchViewModel by viewModels() {
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SearchViewModel(requireContext()) as T
            }

        }
    }
    private lateinit var violationAdapter: ViolationInViolationGroupAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    @ExperimentalCoroutinesApi // cos thay doi gi trong luong corotine hay ko
    @FlowPreview // hieu nang Flow
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        violationAdapter = ViolationInViolationGroupAdapter(requireContext(),lifecycleScope)
        //load data
        viewModel.listViolationUI.observe(viewLifecycleOwner,{list ->
            violationAdapter.submitList(list)
        })
        binding.listViolation.apply {
            adapter = violationAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
            Timber.d("update view")
        }

        binding.searchView.setOnQueryTextListener(this)

        // chuyen man hinh sang index fragment
        viewModel.navigateIndex.observe(viewLifecycleOwner,EventObserver{
            findNavController().navigate(it)
        })

        //Submit theo key cua search
        viewModel.listViolationFlow.observe(viewLifecycleOwner, {list ->
            violationAdapter.updateList(list)
            Timber.d("update lai list")
        })
    }

    @ExperimentalCoroutinesApi
    override fun onQueryTextSubmit(p0: String?): Boolean {
        if(binding.listViolation.visibility == View.GONE){
            binding.listViolation.visibility = View.VISIBLE
        }
        return false
    }

    @ExperimentalCoroutinesApi
    override fun onQueryTextChange(p0: String?): Boolean {
        if(binding.listViolation.visibility == View.GONE){
            binding.listViolation.visibility = View.VISIBLE
        }
        //logicSearch(p0)
        p0?.let {
            viewModel.setQuerySearch(it)
        }
        return false
    }

    // 2 fun duoi theo logic cua db chua fix dc vi no loan vcl
    private fun logicSearchUpdate(searchString: String?){

        viewModel.listKeyWord.observe(viewLifecycleOwner, {list ->
            if(!searchString.isNullOrEmpty()) {
                val listKeyWord = list.filter {
                    it.name!!.contains(searchString, false)
                } as ArrayList<KeyWord>

                viewModel.listKeyWordDetail.observe(viewLifecycleOwner, { listDetail ->

                    var listID = ArrayList<Long>()

                    listKeyWord.forEach { keyWord ->
                        val list1 = listDetail.filter { keyWordDetail ->
                            keyWord.id!! == keyWordDetail.keyWordId
                        }
                        list1.forEach { item ->
                            item.violationId?.let { listID.add(it) }
                        }

                        listID = getEmptyDifferent(listID)
                    }
                    listID.forEach { id ->
                        viewModel.getViolationWithId(id.toInt())
                            .observe(viewLifecycleOwner, { violationUI ->
                                if(violationUI != null){
                                    viewModel._violationListTest.value?.add(violationUI)
                                    viewModel._violationListTest.postValue(viewModel._violationListTest.value)
                                }
                            })
                    }
                })
                viewModel.violationListTest.observe(viewLifecycleOwner,{list ->
                    Timber.d("check size list ${list.size}")
                    violationAdapter.updateList(list)
                })
            }
            else {
                val zeroList = ArrayList<ViolationUI>()
                violationAdapter.updateList(zeroList)
            }
            })
    }

    private fun getEmptyDifferent(list: ArrayList<Long>): ArrayList<Long>{
        list.sort()
        val listNew = ArrayList<Long>()
        var tmp : Long= 0
        list.forEach{
            if(tmp != it){
                listNew.add(it)
            }
            tmp = it
        }
        Timber.d("id list __ ${listNew.size}")
        return listNew
    }
}