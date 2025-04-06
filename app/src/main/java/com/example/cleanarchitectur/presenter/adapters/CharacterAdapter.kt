package com.example.cleanarchitectur.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.cleanarchitectur.domain.model.carton.Character
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitectur.databinding.CharactersItemBinding
import com.example.cleanarchitectur.presenter.loadUrl

class CharacterAdapter : PagingDataAdapter<Character, CharacterAdapter.CharacterViewHolder>(
    object : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
            oldItem == newItem
    }
) {

    inner class CharacterViewHolder(private val binding: CharactersItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.tvName.text = character.name ?: "Без имени"
            binding.tvStatus.text = character.status ?: "Неизвестно"
            binding.ivAvatar.loadUrl(character.image ?: "")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CharactersItemBinding.inflate(inflater, parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        character?.let { holder.bind(it) }
    }
}
