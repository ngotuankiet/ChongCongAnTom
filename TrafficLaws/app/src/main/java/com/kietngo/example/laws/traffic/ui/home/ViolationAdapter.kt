package com.kietngo.example.laws.traffic.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kietngo.example.laws.traffic.databinding.ItemViolationBinding
import com.kietngo.example.laws.traffic.databinding.ItemViolationGroupBinding
import com.kietngo.example.laws.traffic.ui.model.ViolationUI

class ViolationAdapter : ListAdapter<ViolationUI,ViolationAdapter.ViolationViewHolder>(ViolationAdapter.ViolationDiffUtil) {

    private lateinit var binding : ItemViolationBinding

    inner class ViolationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(violationUI : ViolationUI){
            binding.tvTitleViolation.text = violationUI.violation.name
            binding.tvValueViolation.text = violationUI.violation.fines
            binding.tvTransport.text = violationUI.violation.typeId?.let {it ->  setTransport(it) }
            itemView.setOnClickListener {
                violationUI.onClick()
            }
        }
        /**
         * @param numberTransport tham so class violation typeID
         * @return ve ten transport
         * */
        private fun setTransport(numberTransport : Int) : String = when(numberTransport){
            1 -> "Xe Máy"
            2 -> "Ô Tô"
            3 -> "Khác"
            else -> "Khác"
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
        binding = ItemViolationBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViolationViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViolationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}