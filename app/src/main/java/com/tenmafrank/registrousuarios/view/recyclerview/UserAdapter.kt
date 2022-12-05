package com.tenmafrank.registrousuarios.view.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tenmafrank.registrousuarios.R
import com.tenmafrank.registrousuarios.model.DataModel

class UserAdapter(val reciclerData: MutableList<DataModel>): RecyclerView.Adapter<UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater:LayoutInflater = LayoutInflater.from(parent.context)
        return UserViewHolder(layoutInflater.inflate(R.layout.item_user,parent,false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item: DataModel = reciclerData[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = reciclerData.size

}