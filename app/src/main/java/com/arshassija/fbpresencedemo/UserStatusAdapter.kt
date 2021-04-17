package com.arshassija.fbpresencedemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arshassija.fbpresencedemo.databinding.RvItemUserBinding

class UserStatusAdapter : ListAdapter<User, UserStatusAdapter.UserStatusVH>(DIFF_CALLBACK()) {

    private class DIFF_CALLBACK : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            if (oldItem.name == newItem.name && oldItem.status == newItem.status) {
                return true
            }
            return false
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserStatusVH {
        return UserStatusVH(
            RvItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserStatusVH, position: Int) {
        val user = getItem(position)
        holder.binding.tvName.text = user.name
        holder.binding.tvStatus.text = user.status
        if (user.status?.toLowerCase() == "online".toLowerCase()) {
            holder.binding.ivStatus.setBackgroundResource(R.drawable.online_green)
        } else {
            holder.binding.ivStatus.setBackgroundResource(R.drawable.offline_red)
        }
    }

    class UserStatusVH(var binding: RvItemUserBinding) : RecyclerView.ViewHolder(binding.root)

}