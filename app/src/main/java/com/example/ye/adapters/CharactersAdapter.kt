package com.example.ye.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ye.databinding.ItemCharacterBinding
import com.example.ye.model.Character
import com.squareup.picasso.Picasso
import com.example.R


abstract class CharactersAdapter(private var characters: List<Character>) :
    RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    class CharacterViewHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.binding.apply {
            this.character = character
            executePendingBindings() // This will cause the update to execute immediately
            // If you want to use Picasso for the image, you can do so here
            // Ensure you have a valid image URL or URI from the `character.thumbnail` property
            Picasso.get().load(character.thumbnail.path + "." + character.thumbnail.extension)
                .placeholder(R.drawable.placeholder_image)
                .into(holder.binding.characterImage)


            }
        }
    }

    fun getItemCount(): Int {
        val characters = 0
        return characters.size
    }

    fun updateCharacters(newCharacters: List<Character>) {
        val diffCallback = CharactersDiffCallback(characters, newCharacters)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        var characters = newCharacters
        diffResult.dispatchUpdatesTo(new)
    }

    class CharactersDiffCallback(private val oldList: List<Character>, private val newList: List<Character>) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].id == newList[newItemPosition].id
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]
    }

