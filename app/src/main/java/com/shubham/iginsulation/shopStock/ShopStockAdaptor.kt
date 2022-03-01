package com.shubham.iginsulation.shopStock

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shubham.iginsulation.database.shopStockTransaction.ShopStockTransaction
import com.shubham.iginsulation.databinding.FragmentShopStockItemBinding

class ShopStockAdaptor(val clickListener: ShopStockTransactionListener) :
    ListAdapter<ShopStockTransaction, ShopStockAdaptor.ViewHolder>(ShopStockTransactionDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: FragmentShopStockItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: ShopStockTransaction,
            clickListener: ShopStockTransactionListener
        ) {
            binding.shopStockTransaction = item
            binding.executePendingBindings()
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FragmentShopStockItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class ShopStockTransactionDiffCallback : DiffUtil.ItemCallback<ShopStockTransaction>() {
        override fun areItemsTheSame(oldItem: ShopStockTransaction, newItem: ShopStockTransaction): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ShopStockTransaction, newItem: ShopStockTransaction): Boolean {
            return oldItem == newItem
        }
    }

    class ShopStockTransactionListener(val clickListener: (shopStockTransactionId: Long) -> Unit) {
        fun onClick(shopStockTransaction: ShopStockTransaction) = clickListener(shopStockTransaction.id)
    }
}