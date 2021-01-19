package com.kietngo.example.laws.traffic.ui.violation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kietngo.example.laws.traffic.databinding.ItemViolationInViolationGroupBinding
import com.kietngo.example.laws.traffic.ui.model.ViolationUI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class ViolationInViolationGroupAdapter constructor(
    val context: Context,
    val scope: CoroutineScope
)
    : ListAdapter<ViolationUI,ViolationInViolationGroupAdapter.ViolationViewHolder>(ViolationInViolationGroupAdapter.ViolationDiffUtil){
    private lateinit var binding : ItemViolationInViolationGroupBinding

    inner class ViolationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(violationUI : ViolationUI){
            binding.tvTitleViolation.text = violationUI.violation.name
            binding.tvValueViolation.text = violationUI.violation.fines
            binding.tvTransport.text = violationUI.violation.typeId?.let { setTransport(it) }
            val urlImage = violationUI.violation.icon

            Glide.with(itemView).load(urlImage).centerCrop().into(binding.ivViolation)

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
        binding = ItemViolationInViolationGroupBinding
                .inflate(LayoutInflater.from(parent.context),parent, false)
        return ViolationViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViolationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    // Tai sao ko submit luon vi can phai notifiyData de clear item chu ko lon item
    fun updateList(list: List<ViolationUI>){
        submitList(list)
        notifyDataSetChanged()
    }
}