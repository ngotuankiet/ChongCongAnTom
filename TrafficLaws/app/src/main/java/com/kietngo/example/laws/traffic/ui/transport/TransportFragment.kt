package com.kietngo.example.laws.traffic.ui.transport

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.kietngo.example.laws.traffic.R
import com.kietngo.example.laws.traffic.databinding.FragmentTransportBinding
import com.kietngo.example.laws.traffic.databinding.ItemViolationGroupInTransportBinding

class TransportFragment : Fragment() {
    private lateinit var binding: FragmentTransportBinding
    private lateinit var transportAdapter: TransportAdapter
    private val viewModel : TransportViewModel by viewModels() {
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return TransportViewModel(requireContext()) as T
            }

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentTransportBinding.inflate(inflater, container, false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transportAdapter = TransportAdapter()

        viewModel.listViolationGroupUI.observe(viewLifecycleOwner,{
            transportAdapter.submitList(it)
        })

        binding.listViolationGroup.apply {
            adapter = transportAdapter
            layoutManager = GridLayoutManager(requireContext(),2)
        }
    }


}