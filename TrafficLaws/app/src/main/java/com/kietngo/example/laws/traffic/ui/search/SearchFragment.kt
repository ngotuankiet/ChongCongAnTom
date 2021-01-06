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
import com.kietngo.example.laws.traffic.ui.model.ViolationUI
import com.kietngo.example.laws.traffic.ui.violation.ViolationInViolationGroupAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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


        viewModel.navigateIndex.observe(viewLifecycleOwner,EventObserver{
            findNavController().navigate(it)
        })
    }



    override fun onQueryTextSubmit(p0: String?): Boolean {
        if(binding.listViolation.visibility == View.GONE){
            binding.listViolation.visibility = View.VISIBLE
        }
        logicSearch(p0)
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        //logicSearch(p0)
        return false
    }

    private fun logicSearch(searchString: String?){
        viewModel.listViolationUI.observe(viewLifecycleOwner,{list ->
            if(!searchString.isNullOrEmpty()){
                val listSearch = list.filter {
                    it.violation.name!!.contains(searchString,false)
                }
                violationAdapter.updateList(listSearch)
            }
        })

    }
}

// Search con 1 cai logic trong microsoft nma chua lam vi chua co time :v lam UI da~