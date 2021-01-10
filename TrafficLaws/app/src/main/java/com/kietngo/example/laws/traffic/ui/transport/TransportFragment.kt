package com.kietngo.example.laws.traffic.ui.transport

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.kietngo.example.laws.traffic.R
import com.kietngo.example.laws.traffic.databinding.FragmentTransportBinding
import com.kietngo.example.laws.traffic.databinding.ItemViolationGroupInTransportBinding
import com.kietngo.example.laws.traffic.repository.EventObserver
import com.kietngo.example.laws.traffic.ui.transport.TransportFragmentDirections.Companion.actionTransportFragmentToViolationFragment
import timber.log.Timber

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

        val typeID = arguments?.getInt("transportID")

        // set name to transport in toolbar
        binding.tvTransport.text = typeID?.let { it -> setTransport(it) }

        transportAdapter = TransportAdapter()

        viewModel.listViolationGroupUI.observe(viewLifecycleOwner,{
            transportAdapter.submitList(it)
        })

        binding.listViolationGroup.apply {
            adapter = transportAdapter
            layoutManager = GridLayoutManager(requireContext(),2)
        }

        // navigate to violation and post 2 params
        // groupSortID in Violation group class
        // transportID in Transport class
        viewModel.navigateViolation.observe(viewLifecycleOwner,EventObserver{ groupSortId ->
            typeID?.let { transportID ->
                val action = actionTransportFragmentToViolationFragment(transportID,groupSortId)
                findNavController().navigate(action)
            }
        })
    }

    companion object{
        /**
         * convert transport int to string
         * @param numberTransport is type_sort in TransportType class
         * */
        fun setTransport(numberTransport : Int) : String = when(numberTransport){
            1 -> "Xe Máy"
            2 -> "Ô Tô"
            3 -> "Khác"
            else -> "Khác"
        }
    }
}