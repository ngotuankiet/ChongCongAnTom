package com.kietngo.example.laws.traffic.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.*
import com.kietngo.example.laws.traffic.databinding.ItemViolationGroupBinding
import com.kietngo.example.laws.traffic.ui.model.ButtonUI
import com.kietngo.example.laws.traffic.ui.model.ViolationGroupUI
import com.kietngo.example.laws.traffic.ui.model.ViolationUI


class ViolationGroupAdapter constructor(
        val contextFragment: Context,
        val listViolationUI: LiveData<List<ViolationUI>>,
)
    : ListAdapter<ViolationGroupUI, ViolationGroupAdapter.ViolationGroupViewHolder>(ViolationGroupAdapter.ViolationGroupDiffUtil){

    private lateinit var binding: ItemViolationGroupBinding

    inner class ViolationGroupViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun onBind(violationGroupUI: ViolationGroupUI){
            binding.tvViolationGroupName.text = violationGroupUI.violationGroup.groupName

            val violationAdapter = ViolationAdapter()

            listViolationUI.observe(contextFragment as LifecycleOwner, {list ->
                violationAdapter.submitList(list)
            })

            binding.listViolation.apply {
                adapter = violationAdapter
                layoutManager = GridLayoutManager(contextFragment,2,GridLayoutManager.HORIZONTAL, false)
            }

            binding.btnViolationMore.setOnClickListener {
                violationGroupUI.onClick()
            }
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