package com.earl.ktorchatapp.ui.chat.rooms.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.earl.ktorchatapp.databinding.RecyclerMessageItemBinding
import com.earl.ktorchatapp.ui.models.UiMessage

class MessagesRecyclerAdapter : ListAdapter<UiMessage, MessagesRecyclerAdapter.ItemViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = RecyclerMessageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ItemViewHolder(private val binding: RecyclerMessageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UiMessage) {
            item.recyclerDetails(binding.messageText)
        }
    }

    companion object Diff : DiffUtil.ItemCallback<UiMessage>() {

        override fun areItemsTheSame(oldItem: UiMessage, newItem: UiMessage) = oldItem.same(newItem)

        override fun areContentsTheSame(oldItem: UiMessage, newItem: UiMessage) = oldItem.equals(newItem)
    }
}