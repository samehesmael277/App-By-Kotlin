package com.sameh.testerappkotlin.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sameh.testerappkotlin.R
import com.sameh.testerappkotlin.model.entity.User

class UserRecyclerView :RecyclerView.Adapter<UserRecyclerView.UserViewHolder>() {

    var onItemClick : OnItemClick? = null
    private var usersList: List<User> = emptyList()

    fun setList(userList: List<User>) {
        this.usersList = userList
        notifyDataSetChanged()
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val img_userImg: ImageView = itemView.findViewById(R.id.img_item_user)
        private val tv_username: TextView = itemView.findViewById(R.id.tv_item_username)
        private val tv_message: TextView = itemView.findViewById(R.id.tv_item_message)

        fun bind(user: User) {
            img_userImg.setImageResource(user.idUserImg)
            tv_username.text = user.userName
            tv_message.text = user.userMessage

            itemView.setOnClickListener {
                onItemClick?.onClick(user)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user: User = usersList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }
}