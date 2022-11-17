package com.earl.ktorchatapp.ui.chat.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.earl.ktorchatapp.databinding.ContactsRecyclerItemBinding
import com.earl.ktorchatapp.ui.models.UiUserInfo

class ContactsRecyclerAdapter : ListAdapter<UiUserInfo, ContactsRecyclerAdapter.ItemViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ContactsRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ItemViewHolder(private val binding: ContactsRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UiUserInfo) {
            item.recyclerDetails(
                binding.userAvatar,
                binding.userName
            )
        }
    }

    companion object Diff : DiffUtil.ItemCallback<UiUserInfo>() {

        override fun areItemsTheSame(oldItem: UiUserInfo, newItem: UiUserInfo) = oldItem.same(newItem)

        override fun areContentsTheSame(oldItem: UiUserInfo, newItem: UiUserInfo) = oldItem.equals(newItem)
    }
}