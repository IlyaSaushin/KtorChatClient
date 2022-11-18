package com.earl.ktorchatapp.ui.chat.contacts.addContacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.earl.ktorchatapp.databinding.GlobalUsersRecyclerItemBinding
import com.earl.ktorchatapp.ui.models.UiUserInfo

interface GlobalUserCLickListener {
    fun addUserToContacts(username: String)
}

class PossibleContactsRecyclerAdapter(
    private val cLickListener: GlobalUserCLickListener
) : ListAdapter<UiUserInfo, PossibleContactsRecyclerAdapter.ItemViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = GlobalUsersRecyclerItemBinding.inflate(LayoutInflater.from(parent.context) , parent, false)
        return ItemViewHolder(binding, cLickListener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.addUserToContacts(item.username())
    }

    inner class ItemViewHolder(
        private val binding: GlobalUsersRecyclerItemBinding,
        private val cLickListener: GlobalUserCLickListener
    ) : RecyclerView.ViewHolder(binding.root), GlobalUserCLickListener {

        fun bind(item: UiUserInfo) {
            item.globalUsersRecyclerDetails(
                binding.userImage,
                binding.userName
            )
        }

        override fun addUserToContacts(username: String) {
            binding.addUserBtn.setOnClickListener {
                cLickListener.addUserToContacts(username)
                binding.addUserBtn.visibility = View.INVISIBLE
                binding.addUserAdded.visibility = View.VISIBLE
            }
        }
    }

    companion object Diff : DiffUtil.ItemCallback<UiUserInfo>() {

        override fun areItemsTheSame(oldItem: UiUserInfo, newItem: UiUserInfo) = oldItem.same(newItem)

        override fun areContentsTheSame(oldItem: UiUserInfo, newItem: UiUserInfo) = oldItem.equals(newItem)
    }
}