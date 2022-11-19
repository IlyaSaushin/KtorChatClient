package com.earl.ktorchatapp.ui.chat.rooms.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.earl.ktorchatapp.R
import com.earl.ktorchatapp.databinding.RecyclerAuthorMessageBinding
import com.earl.ktorchatapp.databinding.RecyclerContactMessageBinding
import com.earl.ktorchatapp.ui.models.UiMessage

class MessagesRecyclerAdapter(
    private val userToken: String
) : ListAdapter<UiMessage, BaseViewHolder>(Diff) {

    override fun getItemViewType(position: Int) =
        when(getItem(position).isAuthoredMessage(userToken)) {
            true -> {
                AUTHOR_VIEW_TYPE
            }
            false -> {
                CONTACTS_VIEW_TYPE
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when(viewType) {
        AUTHOR_VIEW_TYPE -> {
            BaseViewHolder.AuthorMessage(
                RecyclerAuthorMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
        CONTACTS_VIEW_TYPE -> {
            BaseViewHolder.ContactMessage(
                RecyclerContactMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
        else -> throw IllegalStateException("Unknown view type")
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object Diff : DiffUtil.ItemCallback<UiMessage>() {

        override fun areItemsTheSame(oldItem: UiMessage, newItem: UiMessage) = oldItem.same(newItem)

        override fun areContentsTheSame(oldItem: UiMessage, newItem: UiMessage) = oldItem.equals(newItem)

        private const val AUTHOR_VIEW_TYPE = 0
        private const val CONTACTS_VIEW_TYPE = 1
    }
}

abstract class BaseViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    open fun bind(item: UiMessage) {}

    class AuthorMessage(
        private val binding: RecyclerAuthorMessageBinding
    ) : BaseViewHolder(binding.root) {
        override fun bind(item: UiMessage) {
            item.recyclerDetails(binding.messageText)
        }
    }

    class ContactMessage(
        private val binding: RecyclerContactMessageBinding
    ) : BaseViewHolder(binding.root) {
        override fun bind(item: UiMessage) {
            item.recyclerDetails(binding.messageText)
        }
    }
}