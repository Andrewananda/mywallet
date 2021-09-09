package com.devstart.mywallet.dashboard.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devstart.mywallet.data.model.Transaction
import com.devstart.mywallet.databinding.RecentItemBinding

class TransactionsAdapter : ListAdapter<Transaction, TransactionsAdapter.TransactionViewHolder>(
    TransactionDiffUtil
) {

    inner class TransactionViewHolder(private val binding: RecentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: Transaction) {
            binding.title.text = transaction.title
            binding.amount.text = transaction.amount.toString()
            binding.date.text = transaction.date
        }
    }


    companion object TransactionDiffUtil : DiffUtil.ItemCallback<Transaction>() {
        override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
            return newItem.id == oldItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder(RecentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}