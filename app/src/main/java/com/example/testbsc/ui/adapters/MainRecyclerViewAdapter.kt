package com.example.testbsc.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.testbsc.R
import com.example.testbsc.data.Member

class MainRecyclerViewAdapter :
    RecyclerView.Adapter<MainRecyclerViewAdapter.MainRecyclerViewHolder>() {

    var members = listOf<Member>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerViewHolder {
        return MainRecyclerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_member, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainRecyclerViewHolder, position: Int) {
        holder.bind(members[position])
    }

    override fun getItemCount(): Int {
        return members.size
    }

    inner class MainRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(member: Member) = with(itemView) {
            findViewById<ImageView>(R.id.itemMemberImageView).load(member.image)
            findViewById<TextView>(R.id.itemMemberName).text = member.name
        }
    }

}