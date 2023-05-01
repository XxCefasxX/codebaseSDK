package com.cfsproj.code_base_sdk.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cfsproj.code_base_sdk.databinding.CharacterItemBinding
import com.cfsproj.code_base_sdk.domain.DomainChar

/**
 * Adapter for the [RecyclerView] in [dev.xascar.simpsonsapp]. Displays [DomainChar] data object.
 */
class CharactersAdapter(private val onItemClicked: (character: DomainChar) -> Unit ) : ListAdapter<DomainChar, CharactersAdapter.CharacterViewHolder>(
    DiffCallback
) {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just an Affirmation object.
    class CharacterViewHolder(private val binding: CharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DomainChar) {
            binding.apply {
                tvCharacterName.text = item.name
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(CharacterItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        try {
            val item = getItem(position)
            holder.itemView.setOnClickListener {
                onItemClicked(item)
            }
            holder.bind(item)
        }catch (e: Exception){
            Log.d("HomeListAdapter", "onBindViewHolder: $e")
        }
    }



    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<DomainChar>() {
            override fun areItemsTheSame(oldItem: DomainChar, newItem: DomainChar): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: DomainChar, newItem: DomainChar): Boolean {
                return oldItem == newItem
            }
        }

    }
}


