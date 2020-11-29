package com.kietngo.example.laws.traffic.ui.violation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kietngo.example.laws.traffic.R
import com.kietngo.example.laws.traffic.base.BaseFragment
import com.kietngo.example.laws.traffic.databinding.FragmentViolationBinding
import com.kietngo.example.laws.traffic.repository.EventObserver
import com.kietngo.example.laws.traffic.ui.share.ShareViewModel
import timber.log.Timber

class ViolationFragment : BaseFragment() {

    private lateinit var binding: FragmentViolationBinding
    private val viewModel : ViolationViewModel by viewModels() {
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return activity?.application?.let { ViolationViewModel(it) } as T
            }

        }
    }
    private val shareViewModel : ShareViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentViolationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val violationInViolationGroupAdapter = ViolationInViolationGroupAdapter(requireContext(),lifecycleScope)

        //back
        binding.btnBack.setOnClickListener { findNavController().navigateUp() }


        //test
        shareViewModel.shareViolationGroupIdToGet.observe(viewLifecycleOwner,{
            viewModel.getAllListViolationUIWithId(it).observe(viewLifecycleOwner,{list ->
                violationInViolationGroupAdapter.submitList(list)
            })

            viewModel.getViolationGroupUiWithGroupId(it).observe(viewLifecycleOwner,{ violationGroup ->
                binding.tvTitleViolationGroup.text = violationGroup.violationGroup.groupName
            })
        })

        binding.listViolation.apply {
            adapter = violationInViolationGroupAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        }

        viewModel.navigateIndex.observe(viewLifecycleOwner, EventObserver{
            findNavController().navigate(it)
        })
    }


}