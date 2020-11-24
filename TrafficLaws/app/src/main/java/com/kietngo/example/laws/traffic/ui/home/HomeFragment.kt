package com.kietngo.example.laws.traffic.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kietngo.example.laws.traffic.R
import com.kietngo.example.laws.traffic.base.BaseFragment
import com.kietngo.example.laws.traffic.databinding.FragmentHomeBinding
import com.kietngo.example.laws.traffic.repository.EventObserver
import com.kietngo.example.laws.traffic.ui.model.ViolationUI
import timber.log.Timber
import java.util.*

class HomeFragment : BaseFragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var violationGroupAdapter: ViolationGroupAdapter
    private val viewModel : HomeViewModel by viewModels(){
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return activity?.application?.let { HomeViewModel(it) } as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        violationGroupAdapter = ViolationGroupAdapter(requireContext(),viewModel.listViolationUI,viewModel.btnViolation)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.listViolationGroupUI.observe(viewLifecycleOwner, {
            violationGroupAdapter.submitList(it)
        })

        binding.listViolationGroup.apply {
            adapter = violationGroupAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        }

        viewModel.btnViolation.observe(viewLifecycleOwner, {
            violationGroupAdapter.onClickMore ={
                it.onClick()
            }
        })

        viewModel.navigateViolation.observe(viewLifecycleOwner, EventObserver{
            if(it){
                val action = HomeFragmentDirections.actionHomeFragmentToViolationFragment()
                findNavController().navigate(action)
            }
        })
    }
}