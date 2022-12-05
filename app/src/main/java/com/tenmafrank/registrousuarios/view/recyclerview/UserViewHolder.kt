package com.tenmafrank.registrousuarios.view.recyclerview

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tenmafrank.registrousuarios.databinding.ItemUserBinding
import com.tenmafrank.registrousuarios.model.DataModel
import com.tenmafrank.registrousuarios.utils.Toster

class UserViewHolder(view: View):RecyclerView.ViewHolder(view) {
    private val toster = Toster()
    private val binding = ItemUserBinding.bind(view)

    fun bind(data: DataModel){
        Picasso.get().load(data.avatar).into(binding.avatarImageView)
        binding.emailTextView.text = data.email
        binding.firstNameTextView.text = data.firstName
        binding.lastNameTextView.text = data.lastName

        binding.updateUserItemButton.setOnClickListener {
            toster.makeAToast(binding.root.context,"Falta implementar")
        }

        binding.deleteUserItemButton.setOnClickListener {
            toster.makeAToast(binding.root.context,"Falta implementar")
        }

    }
}