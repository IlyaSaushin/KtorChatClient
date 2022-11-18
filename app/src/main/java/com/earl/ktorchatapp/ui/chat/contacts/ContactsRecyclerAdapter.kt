package com.earl.ktorchatapp.ui.chat.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.earl.ktorchatapp.databinding.ContactsRecyclerItemBinding
import com.earl.ktorchatapp.ui.models.UiUserInfo

interface OnContactClickListener {
    fun removeUserFromContacts(username: String)
}

class ContactsRecyclerAdapter(
    private val clickListener: OnContactClickListener
) : ListAdapter<UiUserInfo, ContactsRecyclerAdapter.ItemViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ContactsRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.removeUserFromContacts(item.username())
    }

    inner class ItemViewHolder(
        private val binding: ContactsRecyclerItemBinding,
        private val clickListener: OnContactClickListener
    ) : RecyclerView.ViewHolder(binding.root), OnContactClickListener {
        fun bind(item: UiUserInfo) {
            item.contactsRecyclerDetails(
                binding.userAvatar,
                binding.userName
            )
        }

        override fun removeUserFromContacts(username: String) {
            binding.deleteProfile.setOnClickListener {
                clickListener.removeUserFromContacts(username)
            }
        }
    }

    companion object Diff : DiffUtil.ItemCallback<UiUserInfo>() {

        override fun areItemsTheSame(oldItem: UiUserInfo, newItem: UiUserInfo) = oldItem.same(newItem)

        override fun areContentsTheSame(oldItem: UiUserInfo, newItem: UiUserInfo) = oldItem.equals(newItem)
    }
}