package com.kietngo.example.laws.traffic.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kietngo.example.laws.traffic.databinding.ItemViolationGroupBinding
import com.kietngo.example.laws.traffic.ui.model.ViolationGroupUI
import timber.log.Timber

class ViolationGroupAdapter
    : ListAdapter<ViolationGroupUI, ViolationGroupAdapter.ViolationGroupViewHolder>(ViolationGroupAdapter.ViolationGroupDiffUtil){

    private lateinit var binding: ItemViolationGroupBinding

    inner class ViolationGroupViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun onBind(violationGroupUI: ViolationGroupUI){
            Timber.d("item item")
        }
    }

    object ViolationGroupDiffUtil: DiffUtil.ItemCallback<ViolationGroupUI>(){
        override fun areItemsTheSame(oldItem: ViolationGroupUI, newItem: ViolationGroupUI): Boolean {
            return oldItem.violationGroup == newItem.violationGroup
        }

        override fun areContentsTheSame(oldItem: ViolationGroupUI, newItem: ViolationGroupUI): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViolationGroupViewHolder {
        binding = ItemViolationGroupBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViolationGroupViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViolationGroupViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}