package com.earl.ktorchatapp.ui.chat.rooms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.earl.ktorchatapp.databinding.RoomRecyclerItemBinding
import com.earl.ktorchatapp.ui.models.UiChatRoom

interface OnRoomClickListener{
    fun joinChatRoom(token: String)
}

class RoomsRecyclerAdapter(
    private val clickListener: OnRoomClickListener
) : ListAdapter<UiChatRoom, RoomsRecyclerAdapter.ItemViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = RoomRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            clickListener.joinChatRoom(item.token())
        }
    }

    inner class ItemViewHolder(private val binding: RoomRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UiChatRoom) {
            item.details(binding.userName)
        }
    }

    companion object Diff : DiffUtil.ItemCallback<UiChatRoom>() {

        override fun areItemsTheSame(oldItem: UiChatRoom, newItem: UiChatRoom) = oldItem.same(newItem)

        override fun areContentsTheSame(oldItem: UiChatRoom, newItem: UiChatRoom) = oldItem.equals(newItem)
    }
}