package com.kietngo.example.laws.traffic.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kietngo.example.laws.traffic.databinding.ItemTransportBinding
import com.kietngo.example.laws.traffic.ui.model.TransportUI
import timber.log.Timber

class TransportAdapter(val context: Context) : ListAdapter<TransportUI, TransportAdapter.TransportViewHolder>(TransportAdapter.TransportDiffUtil){

    private lateinit var binding : ItemTransportBinding

    inner class TransportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun onBind(transportUI: TransportUI){

            binding.btnTransport.text = transportUI.transportType.typeName
            binding.btnTransport.setOnClickListener {
                transportUI.onClick()
                Timber.d("navigate to transport Fragment")
            }
        }
    }

    object TransportDiffUtil : DiffUtil.ItemCallback<TransportUI>(){
        override fun areItemsTheSame(oldItem: TransportUI, newItem: TransportUI): Boolean {
            return oldItem.transportType == newItem.transportType
        }

        override fun areContentsTheSame(oldItem: TransportUI, newItem: TransportUI): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransportViewHolder {
        binding = ItemTransportBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TransportViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: TransportViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}