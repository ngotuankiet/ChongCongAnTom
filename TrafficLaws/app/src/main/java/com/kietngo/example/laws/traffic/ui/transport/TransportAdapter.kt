package com.kietngo.example.laws.traffic.ui.transport

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kietngo.example.laws.traffic.databinding.ItemViolationGroupInTransportBinding
import com.kietngo.example.laws.traffic.repository.room.model.violationgroup.ViolationGroup
import com.kietngo.example.laws.traffic.ui.model.ViolationGroupUI
import timber.log.Timber

class TransportAdapter: ListAdapter<ViolationGroupUI,TransportAdapter.TransportViewHolder>(TransportAdapter.TransportDiffUtil) {

    private lateinit var binding: ItemViolationGroupInTransportBinding

    inner class TransportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(violationGroupUI: ViolationGroupUI){
            binding.tvViolationGroup.text = violationGroupUI.violationGroup.groupName
            itemView.setOnClickListener {
                violationGroupUI.onClick()
                Timber.d("navigate violation with transport")
            }
        }
    }

    object TransportDiffUtil: DiffUtil.ItemCallback<ViolationGroupUI>(){
        override fun areItemsTheSame(oldItem: ViolationGroupUI, newItem: ViolationGroupUI): Boolean {
            return oldItem.violationGroup.groupName == newItem.violationGroup.groupName
        }

        override fun areContentsTheSame(oldItem: ViolationGroupUI, newItem: ViolationGroupUI): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransportViewHolder {
        binding = ItemViolationGroupInTransportBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TransportViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: TransportViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}