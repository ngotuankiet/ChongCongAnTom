package com.kietngo.example.laws.traffic.ui.violation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kietngo.example.laws.traffic.databinding.ItemViolationInViolationGroupBinding
import com.kietngo.example.laws.traffic.ui.model.ViolationUI

class ViolationInViolationGroupAdapter
    : ListAdapter<ViolationUI,ViolationInViolationGroupAdapter.ViolationViewHolder>(ViolationInViolationGroupAdapter.ViolationDiffUtil){
    private lateinit var binding : ItemViolationInViolationGroupBinding

    inner class ViolationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(violationUI : ViolationUI){
            binding.tvTitleViolation.text = violationUI.violation.name
            binding.tvValueViolation.text = violationUI.violation.fines

            itemView.setOnClickListener {
                violationUI.onClick()
            }
        }

    }

    object ViolationDiffUtil: DiffUtil.ItemCallback<ViolationUI>(){
        override fun areContentsTheSame(oldItem: ViolationUI, newItem: ViolationUI): Boolean {
            return oldItem.violation == newItem.violation
        }

        override fun areItemsTheSame(oldItem: ViolationUI, newItem: ViolationUI): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViolationViewHolder {
        binding = ItemViolationInViolationGroupBinding
                .inflate(LayoutInflater.from(parent.context),parent, false)
        return ViolationViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViolationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}