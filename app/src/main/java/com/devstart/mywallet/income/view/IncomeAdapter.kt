package com.devstart.mywallet.income.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devstart.mywallet.data.model.Income
import com.devstart.mywallet.databinding.IncomeItemBinding

class IncomeAdapter : ListAdapter<Income, IncomeAdapter.IncomeViewHolder>(IncomeDiffUtil) {

    inner class IncomeViewHolder(private val binding: IncomeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item : Income) {
            binding.date.text = item.date
            binding.amount.text = "Kes " + item.amount.toString()
            binding.description.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeViewHolder {
         return IncomeViewHolder(IncomeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: IncomeViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object IncomeDiffUtil: DiffUtil.ItemCallback<Income>() {
        override fun areItemsTheSame(oldItem: Income, newItem: Income): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Income, newItem: Income): Boolean {
            return newItem.id == oldItem.id
        }
    }
}