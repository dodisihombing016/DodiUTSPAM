package com.dodiutspam.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dodiutspam.R

class UserAdapter(private val userList: List<itemViewModel>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.findViewById(R.id.card_name)
        val userId: TextView = itemView.findViewById(R.id.card_id)
        // Add other views as needed
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        val nama = user.username
        val id = user.id
        holder.userName.text = "Nama: $nama"
        holder.userId.text = "ID: $id"
        // Bind other user data to the views
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}